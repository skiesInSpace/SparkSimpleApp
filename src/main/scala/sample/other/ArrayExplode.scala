package sample.other

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

import scala.util.Random

object ArrayExplode {
  case class Node(node_id: Int, lon: Double, lat: Double)

  case class Way(way_id: Int, nodes_ids: Array[Int])

  case class WayLength(way_id: Integer, length: Int)

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    /*
    Given 2 datasets:
    Nodes, contains about 500_000_000 elements
    df_nodes => [node_id int, lon float, lat float]
    (123, 135.177597, 33.909979)
    (127, 134.108888, 33.870144)
    …
    */

    import spark.implicits._
    val nodes = List(Node(123, 135.177597, 33.909979), Node(127, 134.108888, 33.870144), Node(135, 264.108888, 58.870144)).toDS()
    nodes.show()
    /*
        Ways, containing about 100_000_000 elements
        df_ways => [way_id int, nodes_ids Array(int)]
        (67812, [123, 127, 135, 199])
        (89833, [125, 11, 333])
       */
    val ways = List(
      Way(67812, Array(123, 127, 135, 199)),
      Way(89833, Array(125, 11, 333))
    ).toDS()
    ways.show()
    /*
        Create a dataset containing ways lengths
        [way_id int, length int]
        To calculate the distance please use the existing udf function
        distance((lat_1, lon_1), (lat_2, lon_2)) -> float
        which calculates the distance between points with latitudes/longitudes lat1, lon1 and lat2, lon2
         */

    val explodedArr = ways.select(ways("way_id"), posexplode(ways("nodes_ids")).as(Array("pos", "node_id")))
    val joined = explodedArr.join(nodes, explodedArr("node_id") === nodes("node_id"))

    val window = Window.orderBy("pos")
    val leadLonCol = lead(col("lon"), 1).over(window)
    val leadLatCol = lead(col("lat"), 1).over(window)

    val distanceUdf = udf((lat_1: Double, lon_1: Double, lat_2: Double, lon_2: Double) => 5 * Random.nextInt(500))
    val distanceCalculated = joined
      .withColumn("lonLead", leadLonCol)
      .withColumn("latLead", leadLatCol)
      .withColumn("distance", distanceUdf(col("lon"), col("lat"), leadLonCol, leadLatCol))
    val cached = distanceCalculated.cache()
    cached.show()
    val groupedByWay = cached
      .groupBy("way_id").sum("distance")
    groupedByWay
      .show()

  }

}
