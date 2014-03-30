package readinglist

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._

case class Book(id: Long, title: String, isRead: Boolean, isbn: String, isStarred: Boolean) {

  def this(title: String, isRead: Boolean, isbn: String, isStarred: Boolean) = {
    this(0, title, isRead, isbn, isStarred)
  }

  def toHash = {
    ("id" -> this.id) ~ ("title" -> this.title) ~ ("isRead" -> this.isRead) ~ ("isbn" -> this.isbn) ~ ("isStarred" -> this.isStarred)
  }

  def toJson = {
    "{id:" + this.id + ",title:" + this.title + ",isRead:" + this.isRead + ",isbn:" + this.isbn + ",isStarred:" + this.isStarred + "}"
  }

  def exists ={
    id > 0
  }

}

object Book {

  def from(json: String): Book = {
    implicit val formats = DefaultFormats
    parse(json).extract[Book]
  }

  def valueOf(json: JValue, field: String): String = {
    compact(render(json \ field))
  }

  def missing = {
    new Book(0, "", false, "", false)
  }

}
