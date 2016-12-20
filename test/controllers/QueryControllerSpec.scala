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
class QueryControllerSpec extends FunSuite with OneAppPerTest {
  override def withFixture(test: NoArgTest): Outcome ={
    super.withFixture(test)
  }

  test("QueryController GET /query should render the index page from a new instance of controller") {
    val controller = new QueryController
    val query = controller.index().apply(FakeRequest())

    assert(status(query) === OK)
    assert(contentType(query) === Some("text/html"))
    assert((contentAsString(query) contains("Query your data")) === true)
  }

  test("QueryController GET /query should render the index page from the application") {
    val controller = app.injector.instanceOf[QueryController]
    val query = controller.index().apply(FakeRequest())

    assert(status(query) === OK)
    assert(contentType(query) === Some("text/html"))
    assert((contentAsString(query) contains ("Query your data")) === true)
  }

  test("QueryController GET /query should render the index page from the router") {
    // Need to specify Host header to get through AllowedHostsFilter
    val request = FakeRequest(GET, "/query").withHeaders("Host" -> "localhost")
    val query = route(app, request).get

    assert(status(query) === OK)
    assert(contentType(query) === Some("text/html"))
    assert((contentAsString(query) contains ("Query your data") ) === true)
  }

  test("QueryController GET /query/country?nameOrCode=BQ should render the query_result page from the router") {
    // Need to specify Host header to get through AllowedHostsFilter
    val request = FakeRequest(GET, "/query/country?nameOrCode=BQ").withHeaders("Host" -> "localhost")
    val query = route(app, request).get

    assert(status(query) === OK)
    assert(contentType(query) === Some("text/html"))
    assert((contentAsString(query) contains ("Below are your results") ) === true)
  }
}
