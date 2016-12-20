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
class HomeControllerSpec extends FunSuite with OneAppPerTest {
   override def withFixture(test: NoArgTest): Outcome ={
     super.withFixture(test)
   }

   test("HomeController GET should render the index page from a new instance of controller") {
      val controller = new HomeController
      val home = controller.index().apply(FakeRequest())

      assert(status(home) === OK)
      assert(contentType(home) === Some("text/html"))
      assert((contentAsString(home) contains("Welcome to Play")) === true)
    }

    test("HomeController GET should render the index page from the application") {
      val controller = app.injector.instanceOf[HomeController]
      val home = controller.index().apply(FakeRequest())

      assert(status(home) === OK)
      assert(contentType(home) === Some("text/html"))
      assert((contentAsString(home) contains ("Welcome to Play")) === true)
    }

    test("HomeController GET should render the index page from the router") {
      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/").withHeaders("Host" -> "localhost")
      val home = route(app, request).get

      assert(status(home) === OK)
      assert(contentType(home) === Some("text/html"))
      assert((contentAsString(home) contains ("Welcome to Play") ) === true)
    }
}
