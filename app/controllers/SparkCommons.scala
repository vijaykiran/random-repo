package controllers

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import com.typesafe.config.ConfigFactory

/**
 * Handles configuration, context and so
 *
 * @author Alexandre Masselot.
 */
object SparkCommons {
  //build the SparkConf  object at once
  lazy val conf = {
    new SparkConf(false)
      .setMaster("local[*]")
      .setAppName("play demo")
      .set("spark.logConf", "true")
  }

  lazy val sc = SparkContext.getOrCreate(conf)
  lazy val sqlContext = new SQLContext(sc)

  val runwayPath = ConfigFactory.load().getString("filepath.runway.value")
  val airportPath = ConfigFactory.load().getString("filepath.airport.value")
  val countryPath = ConfigFactory.load().getString("filepath.country.value")

  def createAirportSchema(): Unit = {
    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(airportPath)
      .toDF().createOrReplaceTempView("airports")
  }

  def createCountrySchema(): Unit = {
    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(countryPath)
      .toDF().createOrReplaceTempView("countries")
  }

  def createRunwaySchema(): Unit = {
    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("nullValue", "null")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load(runwayPath)
      .toDF().createOrReplaceTempView("runways")
  }

  def createAllSchemas(): Unit = {
    createAirportSchema()
    createRunwaySchema()
    createCountrySchema()
  }

}
