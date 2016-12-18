package services

import controllers.SparkCommons._

object AirportService {
  import sqlContext.implicits._
  val airport = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load("resources/airports.csv")
    .toDF().createOrReplaceTempView("airports")

  def getTop10CountriesWithHighestAndLowestNumberOfAirports(): Any ={
    import services.CountryService.country
    sqlContext
      .sql("SELECT * FROM " +
        "(SELECT first(c.name), count(*) as counter FROM airports s JOIN countries c ON s.iso_country = c.code " +
        "GROUP BY s.iso_country ORDER BY counter desc limit 10) first " +
        "UNION ALL " +
        "SELECT * FROM " +
        "(SELECT first(c.name), count(*) as counter from airports s JOIN countries c ON s.iso_country = c.code " +
        "GROUP BY s.iso_country ORDER BY counter asc limit 10) last")
      .collect()
  }
}
