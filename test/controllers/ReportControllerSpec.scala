package controllers

import org.scalatest._
import org.scalatestplus.play.OneAppPerTest
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class ReportControllerSpec extends FunSuite with OneAppPerTest {
  override def withFixture(test: NoArgTest): Outcome ={
    super.withFixture(test)
  }

  test("ReportController GET /report should render the index page from a new instance of controller") {
    val controller = new ReportController
    val report = controller.index().apply(FakeRequest())

    assert(status(report) === OK)
    assert(contentType(report) === Some("text/html"))
    assert((contentAsString(report) contains("The top 10 most common runway identifications")) === true)
    assert((contentAsString(report) contains("10 countries with highest number of airports")) === true)
    assert((contentAsString(report) contains("Type of runways ")) === true)
  }

  test("ReportController GET /report should render the index page from the application") {
    val controller = app.injector.instanceOf[ReportController]
    val report = controller.index().apply(FakeRequest())

    assert(status(report) === OK)
    assert(contentType(report) === Some("text/html"))
    assert((contentAsString(report) contains("The top 10 most common runway identifications")) === true)
    assert((contentAsString(report) contains("10 countries with highest number of airports")) === true)
    assert((contentAsString(report) contains("Type of runways ")) === true)
  }

  test("ReportController GET /report should render the index page from the router") {
    // Need to specify Host header to get through AllowedHostsFilter
    val request = FakeRequest(GET, "/report").withHeaders("Host" -> "localhost")
    val report = route(app, request).get

    assert(status(report) === OK)
    assert(contentType(report) === Some("text/html"))
    assert((contentAsString(report) contains("The top 10 most common runway identifications")) === true)
    assert((contentAsString(report) contains("10 countries with highest number of airports")) === true)
    assert((contentAsString(report) contains("Type of runways ")) === true)
  }
}
