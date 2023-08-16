package sample

import org.apache.spark.sql.SparkSession

object PartitionSize {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    // create an RDD with 4 partitions
    val rdd = spark.sparkContext.parallelize(Seq("foo", "bar", "baz", "qux"), 4)

    // calculate the total size of the RDD
    val totalSize = rdd.map(_.getBytes("UTF-8").length.toLong).reduce(_ + _)

    // calculate the approximate size of each partition
    val partitionSize = totalSize / rdd.getNumPartitions
    println(s"Partition size: $partitionSize bytes")
  }
}
