package sample.other

import com.opencsv.CSVReader
import org.apache.spark.sql.functions.{col, input_file_name, regexp_extract}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SaveMode, SparkSession}

import java.io.StringReader
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object MergeCsvSchemasUsingRdd {

  def createExtraColumnsWithPattern(df: DataFrame, csvPattern: String = ".*/(.*)\\.csv",
                                    idPattern: String = "([A-Z]{2}\\d*_\\d{4}-\\d{2}-\\d{2})_(\\d{8}_\\d{9})"): Dataset[Row] = {
    df
      .withColumn("filepath", input_file_name())
      .withColumn("filename", regexp_extract(col("filepath"), csvPattern, 1))
      .withColumn("COLUMN1", regexp_extract(col("filename"), idPattern, 1))
      .withColumn("COLUMN2", regexp_extract(col("filename"), idPattern, 2))
      .drop("filepath", "filename")
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Spark Kafka Consumer")
      .master("local[*]") // Run Spark locally using all available cores
      .getOrCreate()
    import spark.implicits._
    val ds = spark.read.textFile("C:\\Users\\USER.NAME\\Downloads\\csv_diff\\brokenOpemCsvReading\\32.csv")
      .transform(createExtraColumnsWithPattern(_))
    val rdd = ds.rdd

    val kv = rdd.zipWithIndex().groupBy(row => row._1.toSeq.last.toString).map(tuple => {
      val key = tuple._1
      val headAndValues = tuple._2.toSeq.sortWith((a, b) => a._2 < b._2).map(t => t._1)
      val headRow = headAndValues.head
      val twoLastElements = headRow.toSeq.takeRight(2)
      val (COLUMN1, COLUMN2) = twoLastElements match {
        case Seq(a: String, b: String) => (a, b)
      }
      val header = (headRow.toSeq.dropRight(1).dropRight(1) :+ "COLUMN1" :+ "COLUMN2").mkString(",")
      val parsedColumns = new CSVReader(new StringReader(header)).readAll().head
      //parse header and then zip with each row
      val onlyRows = headAndValues.drop(1)
      val rowsAsStrings = onlyRows.map(row => row.toSeq.dropRight(1).dropRight(1).mkString(","))
      val allRowsMerged = rowsAsStrings.mkString("\r\n")
      val parsedValues = new CSVReader(new StringReader(allRowsMerged)).readAll()
      val valuesEnriched = parsedValues.map(arr => arr :+ COLUMN1 :+ COLUMN2)

      valuesEnriched.map {
        row =>
          val tuples = row.zip(parsedColumns).foldLeft(Map[String, String]()) {
            (map, subLine) => {
              map + (subLine._2 -> subLine._1)
            }
          }
          tuples
      }
    })
    val value = kv.flatMap(args => args)
    val value1 = value.map(map => JsonConverter.toJson(map))
    //        value1.toDF().show(false)
    value1.toDF().coalesce(1).write.mode(SaveMode.Overwrite).text("C:\\Users\\USER.NAME\\Downloads\\csv_diff\\wierdCsvFileInvest")
    //    kv.collect()
  }

}
