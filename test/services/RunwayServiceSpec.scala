package services

import org.scalatest._
import org.apache.spark.sql.Row
import java.io.FileNotFoundException

class RunwayServiceSpec extends FunSuite with BeforeAndAfter {

  test("Get Top 10 Most Common Runway Identifications test"){
    val expected = Array(Row("H1",9), Row("15",5), Row("N",4), Row("08",3), Row("18",3), Row("05",1), Row("06",1), Row("09",1), Row("11",1), Row("14",1))
    val actual = RunwayService.getTop10MostCommonRunwayIdentifications()
    assert(actual === expected)
  }

  test("getTypesOfRunwayPerCountry successul test") {
    val expected = Array(
      Row("CONC","United States"), Row("TURF-F","United States"), Row("TURF","United States"), Row("ASPH-G","Caribbean Netherlands"),
      Row("GRASS","United States"), Row("TURF-G","United States"), Row("GRAVEL","United States"), Row("GRVL","Caribbean Netherlands"),
      Row("MATS","United States"), Row("TURF","Caribbean Netherlands"), Row("ASPH","United States"), Row("ASPH-G","United States"))
    val actual = RunwayService.getTypesOfRunwayPerCountry()
    assert(actual === expected)
  }

  test("getAirportAndRunwaysForCountry when country name is passed as parameter") {
    val expected =  Array(
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",269408,"00A","H1","ASPH-G"),
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",255155,"00AK","N","GRVL"),
      Row("Caribbean Netherlands",6525,"Epps Airpark","small_airport",254165,"00AL","N","TURF"))
    val actual = RunwayService.getAirportAndRunwaysForCountry("Caribbean Netherlands")
    assert(actual === expected)
  }

  test("getAirportAndRunwaysForCountry when partial country name is passed as parameter") {
    val expected =  Array(
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",269408,"00A","H1","ASPH-G"),
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",255155,"00AK","N","GRVL"),
      Row("Caribbean Netherlands",6525,"Epps Airpark","small_airport",254165,"00AL","N","TURF"))
    val actual = RunwayService.getAirportAndRunwaysForCountry("Caribbean")
    assert(actual === expected)
  }

  test("getAirportAndRunwaysForCountry when country code is passed as parameter"){
    val expected = Array(
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",269408,"00A","H1","ASPH-G"),
      Row("Caribbean Netherlands",6523,"Total Rf Heliport","heliport",255155,"00AK","N","GRVL"),
      Row("Caribbean Netherlands",6525,"Epps Airpark","small_airport",254165,"00AL","N","TURF"))
    val actual = RunwayService.getAirportAndRunwaysForCountry("BQ")
    assert(actual === expected)
  }

  test("getAirportAndRunwaysForCountry when passing an empty string") {
    intercept[IllegalArgumentException] {
      RunwayService.getAirportAndRunwaysForCountry("")
    }
  }

  test("getAirportAndRunwaysForCountry when country not found") {
    val expected = Array()
    val actual = RunwayService.getAirportAndRunwaysForCountry("NotFoundCountry")
    assert(actual === expected)
  }
/*
  test("getAirportAndRunwaysForCountry when .CSV file not found"){
    assertThrows[FileNotFoundException]{


    }
  }*/
}
