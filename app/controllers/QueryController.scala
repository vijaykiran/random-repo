package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's query page.
  */
@Singleton
class QueryController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/query`.
    */
  def index = Action {
    implicit request =>
    Ok(views.html.query())
  }

  def searchByCountry() = Action(parse.tolerantFormUrlEncoded) {
    implicit request =>
      val nameOrCode = request.queryString.get("nameOrCode").flatMap(_.headOption).getOrElse("")
      val result = RunwayService.getAirportAndRunwaysForCountry(nameOrCode)
      Ok(views.html.query_results(nameOrCode, result))
  }
}