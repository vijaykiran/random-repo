package services

import org.scalatest._
import org.apache.spark.sql.Row
import java.io.FileNotFoundException
//import com.typesafe.config._

class AirportServiceSpec extends FunSuite with BeforeAndAfter{

  test("Get Top 10 Countries With Highest And Lowest Number Of Airports test"){
    val expected = Array(Row("United States",28), Row("Caribbean Netherlands",2), Row("Caribbean Netherlands",2), Row("United States",28))
    val actual = AirportService.getTop10CountriesWithHighestAndLowestNumberOfAirports()
    assert(actual === expected)
  }

  /*
  test("Get Top 10 Countries With Highest And Lowest Number Of Airports when .CSV file not found test"){
    assertThrows[FileNotFoundException]{
      val oldConfig = ConfigFactory.load
      val newConfig = oldConfig.withValue("filepath.airports.value",ConfigValueFactory.fromAnyRef(456))
      AirportService.getTop10CountriesWithHighestAndLowestNumberOfAirports()
    }
  }*/
}