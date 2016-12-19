package services

import controllers.SparkCommons._
import org.apache.spark.sql.Row

object AirportService {
  import sqlContext.implicits._

  def getTop10CountriesWithHighestAndLowestNumberOfAirports(): Array[Row] = {
    createAirportSchema()
    createCountrySchema()
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
