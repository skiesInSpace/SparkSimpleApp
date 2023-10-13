package sample.other
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
object RddToDFExample {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession.builder()
      .appName("Spark Kafka Consumer")
      .master("local[*]") // Run Spark locally using all available cores
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    import org.apache.spark.sql.types._
    import spark.implicits._

    // Create an RDD
    //    val peopleRDD = spark.sparkContext.textFile("examples/src/main/resources/people.txt")
    val peopleRDD = spark.sparkContext.parallelize(Seq("a,b", "c,d"))

    // The schema is encoded in a string
    val schemaString = "name age"

    // Generate the schema based on the string of schema
    val fields = schemaString.split(" ")
      .map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)

    val value: RDD[Array[String]] = peopleRDD
      .map(_.split(","))

    val rowRDD = value
      .map(attributes => Row(attributes:_*))

    // Apply the schema to the RDD
    val peopleDF = spark.createDataFrame(rowRDD, schema)
    peopleDF.show(false)


  }
}

