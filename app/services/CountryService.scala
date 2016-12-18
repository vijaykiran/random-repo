package services

import controllers.SparkCommons._

object CountryService {
  import sqlContext.implicits._
  val country = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", ",")
    .option("nullValue", "null")
    .option("treatEmptyValuesAsNulls", "true")
    .option("inferSchema", "true")
    .load("resources/countries.csv")
    .toDF().createOrReplaceTempView("countries")

}
