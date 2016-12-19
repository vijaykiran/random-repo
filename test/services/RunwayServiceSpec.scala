package services

class RunwayServiceSpec extends FunSuite with SharedSparkContext{

  before {

  }

  test("Get Top 10 Most Common Runway Identifications test"){
    val expected = []
    assert(RunwayService.getTop10MostCommonRunwayIdentifications() == expected)
  }
}