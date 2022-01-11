
package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}

import slick.jdbc.MySQLProfile.api._



final case class User(id: Long, name: String)
final case class UserDb(tag:Tag) extends Table[User](tag,"userTable"){
  def userId: Rep[String] = column[String]("userId")
  def name: Rep[String] = column[String]("userName")
  def * : ProvenShape[User] =
    (
      userId,
      name
      ).shaped <> (User.tupled, User.unapply)
}
case class UserFormData(name: String)

object UserForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText
    )(UserFormData.apply)(UserFormData.unapply)
  )
}

class UserTableDef(tag: Tag) extends Table[User](tag, "user") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  override def * = (id, name) <> (User.tupled, User.unapply)
}

class UserList @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  var userList = TableQuery[UserTableDef]

  def add(todoItem: User): Future[String] = {
    dbConfig.db
      .run(userList += todoItem)
      .map(res => "TodoItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(userList.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[User]] = {
    dbConfig.db.run(userList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[User]] = {
    dbConfig.db.run(userList.result)
  }

  def update(user: User): Future[Int] = {
    dbConfig.db
      .run(userList.filter(_.id === user.id)
        .map(x => (x.name))
        .update(user.name)
      )
  }




}








