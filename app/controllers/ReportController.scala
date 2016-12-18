package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import models._
import services._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's report page.
  */
@Singleton
class ReportController @Inject() extends Controller {


  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/report`.
    */
  def index = Action {
    implicit request =>
      val data1 = RunwayService.getTop10MostCommonRunwayIdentifications()
      Ok(views.html.report(data1))
  }
}