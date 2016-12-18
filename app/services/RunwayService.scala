package services

//import models.Runway
import controllers.SparkCommons._

object RunwayService {
  import sqlContext.implicits._
  val runway = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load("resources/runways.csv")
    .toDF().createOrReplaceTempView("runway")

  def getTop10MostCommonRunwayIdentifications (): Any = {
      sqlContext
      .sql ("SELECT le_ident, count(*) as count_ident FROM runway GROUP BY le_ident ORDER BY count_ident desc limit 10")
      .collect ()
  }
}