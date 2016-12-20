package services

import com.rockymadden.stringmetric.similarity.JaroMetric

import controllers.SparkCommons._
import org.apache.spark.sql.Row

object CountryService {
  import sqlContext.implicits._

  def getCountryCode(nameOrCode: String): String = nameOrCode.length match  {
    case 0 => throw new IllegalArgumentException()
    case 1 => ""
    case 2 => nameOrCode
    case _ => getCountryCodeByNameFuzzy(nameOrCode)
  }

  def getCountryCodeByNameFuzzy(name: String): String = {
    createCountrySchema()
    val result = sqlContext
      .sql(s"SELECT code, name FROM countries WHERE UPPER(name) LIKE UPPER('$name%')")
      .collect()
      .map(row => (row.getString(0), JaroMetric.compare(name, row.getString(1))
      ))
    if(result.length == 0) "" else returnCode(result.maxBy(_._2))
  }

  def returnCode(result: (String, Option[Double])): String = result._2.get match {
    case 0.0 => ""
    case _ => result._1
  }
}
