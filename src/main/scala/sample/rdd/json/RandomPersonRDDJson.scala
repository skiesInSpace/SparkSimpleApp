package sample.rdd.json

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{Partition, SparkContext, TaskContext}

import scala.reflect.ClassTag
import scala.util.Random

case class Person(name: String, age: Long, col1: String, col2: String, col3: String, col4: String, surname: String)
// Each random partition will hold `numValues` items
 final class RandomPartition[A: ClassTag](val index: Int, numValues: Int, random: => A) extends Partition {
  def values: Iterator[A] = Iterator.fill(numValues)(random)
}

// The RDD will parallelize the workload across `numSlices`
 final class RandomRDD[A: ClassTag](@transient private val sc: SparkContext, numSlices: Int, numValues: Int, random: => A) extends RDD[A](sc, deps = Seq.empty) {

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
      .master("local[10]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    import spark.implicits._
    val rdd = new RandomRDD(spark.sparkContext, 1, 10200000, createPerson)
    rdd.toDS().write.mode("overwrite").json("C:\\Users\\Барков Артур\\IdeaProjects\\SparkSimpleApp\\json-rdd-generated")
  }

  private def createPerson = {
    Person(stringGenerator(), 5, stringGenerator(), stringGenerator(), stringGenerator(), stringGenerator(), stringGenerator())
  }

  def stringGenerator(): String = {
    Random.alphanumeric.take(10).mkString
  }
}