package services

import controllers.SparkCommons._
import com.typesafe.config.ConfigFactory

object CountryService {
  import sqlContext.implicits._
  val countryPath = ConfigFactory.load().getString("filepath.country.value")
  val country = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load(countryPath)
    .toDF().createOrReplaceTempView("countries")
}
