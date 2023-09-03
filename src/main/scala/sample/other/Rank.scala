package sample.other

import org.apache.spark.sql.SparkSession

object Rank {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\WinUtils\\spark-3.2.4-bin-hadoop3.2\\spark-3.2.4-bin-hadoop3.2")
    val spark: SparkSession = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    // rdd API
    //    val rdd = spark.sparkContext.parallelize(List(1, 2, 3, 4, 5, 6, 67, 1, 1, 23, 34, 1))
    //    println(rdd)
    //    println("Num of Partitions: " + rdd.getNumPartitions)

    // Generate random name for big numbers test
    //    spark.sql("INSERT INTO group VALUES (1,'Amy Smith'), (2,'Orny Smith'), (3,'Gorny Smith');")
    //    val frame = spark.sql("select * from group")
    //    val generateRandString = udf((x: Int) => "abc")
    //    spark.udf.
    //    frame.withColumn("extendedName", generateRandString(lit(5)))


    spark.sql("CREATE TABLE IF NOT EXISTS group(id int, name string) USING parquet location 'file:/C:/Users/Artur_Barkou/IdeaProjects/AzurePrototype/spark-warehouse/group'")
    spark.sql("CREATE TABLE IF NOT EXISTS user(\n id int,\n name string,\n rating int,\n grp_id int,\n lead_id int) USING parquet location 'file:/C:/Users/Artur_Barkou/IdeaProjects/AzurePrototype/spark-warehouse/user'")
//    spark.sql("INSERT INTO group VALUES (1,'group1'), (2,'group2'), (3,'group3');")
//    // group 1
//    spark.sql("INSERT INTO user VALUES (1,'Amy Smith',1,1,0), (2,'Art Mmith',2,1,0), (3,'Lmy Amith',3,1,0);")
//    // group 2
//    spark.sql("INSERT INTO user VALUES (3,'Qmy Zmith',1,2,0), (4,'Grt Hmith',2,2,0), (5,'Jmy Kmith',3,2,0);")
//    // group 3
//    spark.sql("INSERT INTO user VALUES (6,'Vmy Bmith',1,3,0), (7,'Nrt YTmith',2,3,0), (8,'Qmy Pmith',3,3,0);")

    //List users who have the highest rating in their groups

    spark.sql("select * from user join group on user.grp_id=group.id").show()
    spark.sql("select * from (select *, rank(rating) over(partition by group.name order by rating DESC) as rank from user join group on user.grp_id=group.id) where rank=1").show()
  }
}
