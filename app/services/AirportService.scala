package services

import controllers.SparkCommons._
import org.apache.spark.sql.Row
import com.typesafe.config.ConfigFactory


object AirportService {
  import sqlContext.implicits._
  val airportPath = ConfigFactory.load().getString("filepath.airport.value")
  val countryPath = ConfigFactory.load().getString("filepath.country.value")
  val airport = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load(airportPath)
    .toDF().createOrReplaceTempView("airports")

  def getTop10CountriesWithHighestAndLowestNumberOfAirports(): Array[Row] = {
    //import services.CountryService.country
    val country = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(countryPath)
      .toDF().createOrReplaceTempView("countries")
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
