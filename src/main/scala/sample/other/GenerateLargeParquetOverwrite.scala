package sample.other

import org.apache.spark.sql.SparkSession

import scala.util.Random

object GenerateLargeParquetOverwrite {

  case class Department(id: String, name: String, col1: String, col2: String, col3: String, col4: String, subDep: String)

  case class Person(name: String, age: Long, col1: String, col2: String, col3: String, col4: String, surname: String)

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    import spark.implicits._
    var persons: Seq[Person] = Seq()
    for (i <- 0 until 1000000) {
      persons = persons :+ createPerson
    }
    persons.toDS().write.mode("overwrite").parquet("C:\\Users\\Барков Артур\\IdeaProjects\\SparkSimpleApp\\parquet-overwrite")

  }

  private def createPerson = {
    Person(stringGenerator(), 5, stringGenerator(), stringGenerator(), stringGenerator(), stringGenerator(), stringGenerator())
  }

  def stringGenerator(): String = {
    Random.alphanumeric.take(10).mkString
  }
}
