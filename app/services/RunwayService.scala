package services

import controllers.SparkCommons._
import org.apache.spark.sql.Row

object RunwayService {
  import sqlContext.implicits._

  def getTop10MostCommonRunwayIdentifications (): Array[Row] = {
    createRunwaySchema()
    sqlContext
      .sql ("SELECT le_ident, count(*) as count_ident FROM runways GROUP BY le_ident ORDER BY count_ident desc, le_ident asc limit 10")
      .collect ()
  }

  def getTypesOfRunwayPerCountry(): Array[Row] = {
    createAllSchemas()
    sqlContext
      .sql("SELECT r.surface, c.name FROM runways r JOIN airports s ON r.airport_ref = s.id JOIN countries c ON s.iso_country = c.code GROUP BY r.surface, c.name")
      .collect()
  }

  def getAirportAndRunwaysForCountry(countryNameOrCode: String): Array[Row] = {
    if (countryNameOrCode.isEmpty) throw new IllegalArgumentException()
    createAllSchemas()
    sqlContext
      .sql("SELECT c.name, s.id, s.name, s.type, r.id, r.airport_ident, r.le_ident, r.surface " +
        "FROM runways r JOIN airports s ON r.airport_ref = s.id JOIN countries c ON s.iso_country = c.code " +
        s"""WHERE c.code=\"$countryNameOrCode\" OR c.name=\"$countryNameOrCode\" """ +
        "ORDER BY s.id")
      .collect()
  }
}