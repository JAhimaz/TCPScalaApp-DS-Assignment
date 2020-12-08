package util

import java.sql.DriverManager

import model.{ShopItem}
import scalikejdbc._

import scala.util.{Failure, Success, Try}

trait Database {
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"
  val dbURL = "jdbc:derby:chatDB;create=true;";
  val dbURLdestroy = "jdbc:derby:chatDB;shutdown=true;"

  Class.forName(derbyDriverClassname)
  ConnectionPool.singleton(dbURL, "me", "mine")
  
  implicit val session = AutoSession

}

object Database extends Database{
  def setupDB() = {
    if (!hasItemsDBInitialize) {
      ShopItem.initializeTable()
      ShopItem.initialSetup()
    }

  }

  def shutdown(): Unit = {
    System.out.println("Shutting down.. DB and Network Server Control..")
    val dbresult = Try(DriverManager.getConnection(dbURLdestroy))

    dbresult match {
      case Success(x) =>
      case Failure(exception) =>
        println("DB shutdown successfully")
        exception.printStackTrace()
    }
  }

  def hasItemsDBInitialize : Boolean = {
    DB getTable "items" match {
      case Some(x) => true
      case None => false
    }
  }

}



