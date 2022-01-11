package controllers.api


import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.User


import models.{User, UserForm}
import play.api.data.FormError
//import play.api.routing.Router.empty.routes
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserController @Inject()(cc: ControllerComponents,userService: UserService) extends AbstractController(cc) {

    implicit val userFormat = Json.format[User]

    def getAll = Action {
      val user = new User(1, "User2")
      Ok(Json.toJson(user))
    }
    def getById(id: Long) = Action.async { implicit request: Request[AnyContent] =>
      userService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }

    def add() = Action.async { implicit request: Request[AnyContent] =>
      UserForm.form.bindFromRequest.fold(
        // if any error in submitted data
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newUser = User(0, data.name)
          userService.addItem(newUser).map( _ => Redirect(routes.UserController.getAll()))
        })
    }

  def update(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    UserForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newUser = User(id, data.name)
        userService.updateItem(newUser).map( _ => Redirect(routes.UserController.getAll))
      })
  }

  def delete(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    userService.deleteItem(id) map { res =>
      Redirect(routes.UserController.getAll)
    }
  }


}
