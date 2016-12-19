package services

import com.typesafe.config.ConfigFactory
import controllers.SparkCommons._
import org.apache.spark.sql.Row

object RunwayService {
  import sqlContext.implicits._
  val runwayPath = ConfigFactory.load().getString("filepath.runway.value")
  val airportPath = ConfigFactory.load().getString("filepath.airport.value")
  val countryPath = ConfigFactory.load().getString("filepath.country.value")
  val runway = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load(runwayPath)
    .toDF().createOrReplaceTempView("runways")

  def getTop10MostCommonRunwayIdentifications (): Array[Row] = {
      sqlContext
      .sql ("SELECT le_ident, count(*) as count_ident FROM runways GROUP BY le_ident ORDER BY count_ident desc, le_ident asc limit 10")
      .collect ()
  }

  def getTypesOfRunwayPerCountry(): Array[Row] = {
    //import services.AirportService.airport
    val airport = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(airportPath)
      .toDF().createOrReplaceTempView("airports")
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
      .sql("SELECT r.surface, c.name FROM runways r JOIN airports s ON r.airport_ref = s.id JOIN countries c ON s.iso_country = c.code GROUP BY r.surface, c.name")
      .collect()
  }

  def getAirportAndRunwaysForCountry(countryNameOrCode: String): Array[Row] = {
    //import services.AirportService.airport
    //import services.CountryService.country
    if (countryNameOrCode.isEmpty) throw new IllegalArgumentException()
    val country = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(countryPath)
      .toDF().createOrReplaceTempView("countries")
    val airport = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(airportPath)
      .toDF().createOrReplaceTempView("airports")
    sqlContext
      .sql("SELECT c.name, s.id, s.name, s.type, r.id, r.airport_ident, r.le_ident, r.surface " +
        "FROM runways r JOIN airports s ON r.airport_ref = s.id JOIN countries c ON s.iso_country = c.code " +
        s"""WHERE c.code=\"$countryNameOrCode\" OR c.name=\"$countryNameOrCode\" """ +
        "ORDER BY s.id")
      .collect()
  }
}