package services

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
    .toDF().createOrReplaceTempView("runways")

  def getTop10MostCommonRunwayIdentifications (): Any = {
      sqlContext
      .sql ("SELECT le_ident, count(*) as count_ident FROM runways GROUP BY le_ident ORDER BY count_ident desc limit 10")
      .collect ()
  }

  def getTypesOfRunwayPerCountry(): Any = {
    import services.AirportService.airport
    import services.CountryService.country
    sqlContext
      .sql("SELECT r.surface, c.name FROM runways r JOIN airports s ON r.airport_ref = s.id JOIN countries c ON s.iso_country = c.code GROUP BY r.surface, c.name")
      .collect()
  }
}