//package services
//
//package services
//
//import models._
//
//import java.util.Date
//import java.sql.{Connection, ResultSet}
//import javax.inject._
//import scala.collection.mutable._
//
//class Operation @Inject() (fc: Factory, hash: Hash) {
//
//  private var id: Int = 0
//
//  private var people = ListBuffer[Person]()
//
//  private var conn: Connection = null
//
//  def findByName(name: String = ""): ListBuffer[Person] = {
//    try {
//      val rs: ResultSet = fc.connect.createStatement.executeQuery(s"SELECT * FROM PERSON WHERE PERSON.NAME LIKE '${name}%';")
//      val list: ListBuffer[User] = this.resultsetTolistBuffer(rs)
//      fc.close
//      return list
//    } catch {
//      case _ => {
//        fc.close
//        return null
//      }
//    }
//  }
//
//
//
//  def savePerson(form: PersonForm): Boolean = {
//    try {
//      this.conn = fc.connect
//      val p: Person = this.createPerson(form)
//      if (p.id > 0) {
//        this.conn.createStatement.executeUpdate(s"INSERT INTO PERSON VALUES(${p.id},'${p.name}','${p.email}',${p.salary},'${p.birthday}','${p.gender}');")
//        this.conn.commit
//        fc.close
//        return true
//      }
//      fc.close
//      return false
//    } catch {
//      case _ => {
//        this.conn.rollback
//        fc.close
//      }
//    }
//    return false
//  }
//
//
//
//  private def resultsetTolistBuffer(rs: ResultSet): ListBuffer[Person] = {
//    var list: ListBuffer[Person] = ListBuffer[Person]()
//    while (rs.next()) {
//      list += this.createPerson(rs)
//    }
//    return list
//  }
//
//  private def createPerson[T](t: T): Person = {
//
//    if (t.getClass.getSimpleName.toString == "HikariProxyResultSet") {
//      val rs = t.asInstanceOf[ResultSet]
//      return new Person(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getBigDecimal("salary"), rs.getDate("birthday"), rs.getString("gender").charAt(0))
//    }
//
//    if (t.getClass.getSimpleName.toString == "PersonForm") {
//      val pf = t.asInstanceOf[PersonForm]
//      if (this.id > 0) {
//        return new Person(this.id, pf.name.toString, pf.email.toString, BigDecimal(pf.salary.toString), new Date(pf.birthday.toString), pf.gender.charAt(0))
//      }
//      return new Person(this.generateID, pf.name.toString, pf.email.toString, BigDecimal(pf.salary.toString), new Date(pf.birthday.toString), pf.gender.charAt(0))
//    }
//    return null
//  }
//
//
//
//}