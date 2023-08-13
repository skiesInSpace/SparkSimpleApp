package sample

import org.apache.spark.sql.SparkSession

import java.util.Properties

object Main {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    val rdd = spark.sparkContext.parallelize(List(1,2,3,4,5,6,67,1,1,23,34,1))

    println(rdd)
    println("Num of Partitions: " + rdd.getNumPartitions)
    spark.sql("CREATE TABLE group(id int, name string) USING parquet;")
    spark.sql("CREATE TABLE group(id int, name string) USING parquet;")
    val frame = spark.sql("select * from group;")
    frame.show()
  }
}
