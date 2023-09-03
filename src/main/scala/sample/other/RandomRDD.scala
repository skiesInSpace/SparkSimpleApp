package sample.other

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{Partition, SparkContext, TaskContext}

import scala.reflect.ClassTag

// Each random partition will hold `numValues` items
private final class RandomPartition[A: ClassTag](val index: Int, numValues: Int, random: => A) extends Partition {
  def values: Iterator[A] = Iterator.fill(numValues)(random)
}

// The RDD will parallelize the workload across `numSlices`
private final class RandomRDD[A: ClassTag](@transient private val sc: SparkContext, numSlices: Int, numValues: Int, random: => A) extends RDD[A](sc, deps = Seq.empty) {

  // Based on the item and executor count, determine how many values are
  // computed in each executor. Distribute the rest evenly (if any).
  private val valuesPerSlice = numValues / numSlices
  private val slicesWithExtraItem = numValues % numSlices

  // Just ask the partition for the data
  override def compute(split: Partition, context: TaskContext): Iterator[A] =
    split.asInstanceOf[RandomPartition[A]].values

  // Generate the partitions so that the load is as evenly spread as possible
  // e.g. 10 partition and 22 items -> 2 slices with 3 items and 8 slices with 2
  override protected def getPartitions: Array[Partition] =
    ((0 until slicesWithExtraItem)
      .view
      .map(new RandomPartition[A](_, valuesPerSlice + 1, random))
      ++ (slicesWithExtraItem until numSlices)
      .view
      .map(new RandomPartition[A](_, valuesPerSlice, random)))
      .toArray

}

object main {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[5]")
      .appName("SparkByExamples.com")
      .getOrCreate()
    val rdd = new RandomRDD(spark.sparkContext, 10, 22, scala.util.Random.nextInt(100) + 1)
    rdd.foreach(println)
  }
}