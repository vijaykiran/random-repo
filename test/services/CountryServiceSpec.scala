package services

import org.scalatest._

class CountryServiceSpec extends FunSuite with BeforeAndAfter{

  test("getCountryCode when valid code is passed as parameter"){
    assert("US" === CountryService.getCountryCode("US"))
  }

  test("getCountryCode when existing full name is passed as parameter"){
    assert("MD" === CountryService.getCountryCode("Moldova"))
  }

  test("getCountryCode when existing partial name is passed as parameter"){
    assert("ZW" === CountryService.getCountryCode("zimb"))
  }

  test("getCountryCode when country is not found"){
    assert("" === CountryService.getCountryCode("not found country"))
  }
}