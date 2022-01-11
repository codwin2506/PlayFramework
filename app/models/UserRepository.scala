//
//import models.User
//
//import javax.inject.{Inject, Singleton}
//import play.api.db.slick.DatabaseConfigProvider
//import slick.jdbc.JdbcProfile
//
//import scala.concurrent.{ExecutionContext, Future}
//
///**
// * A repository for people.
// *
// * @param dbConfigProvider The Play db config provider. Play will inject this for you.
// */
//@Singleton
//class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
//  private val dbConfig = dbConfigProvider.get[JdbcProfile]
//  import dbConfig._
//  import profile.api._
//
//  /**
//   * Here we define the table. It will have a name of people
//   */
//  private class UserTable(tag: Tag) extends Table[User](tag, "Users") {
//
//    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
//
//    def name = column[String]("UserName")
//
//    def * = (id, name) <> ((User.apply _).tupled, User.unapply)
//  }
//
//  private val userList = TableQuery[UserTable]
//
//
//  def list(): Future[Seq[User]] = db.run {
//    userList.result
//  }
//
//  def add(addUser: User): Future[String] = {
//    dbConfig.db
//      .run(userList += addUser)
//      .map(res => "TodoItem successfully added")
//      .recover {
//        case ex: Exception => {
//          printf(ex.getMessage)
//          ex.getMessage
//        }
//      }
//  }
//}