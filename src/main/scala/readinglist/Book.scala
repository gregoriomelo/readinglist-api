package readinglist

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._

case class Book(id: Long, title: String, isRead: Boolean, isbn: String, isStarred: Boolean) {

  def toHash = {
    ("id" -> this.id) ~ ("title" -> this.title) ~ ("isRead" -> this.isRead) ~ ("isbn" -> this.isbn) ~ ("isStarred" -> this.isStarred)
  }

  def toJson = {
    compact(render(this.toHash))
  }

}

object Book {

  def from(json: String): Book = {
    val jsonData = parse(json)
    val title = valueOf(jsonData, "title")
    val isRead = valueOf(jsonData, "isRead").toBoolean
    val isbn = valueOf(jsonData, "isbn")
    val isStarred = valueOf(jsonData, "isStarred").toBoolean

    new Book(0, title, isRead, isbn, isStarred)
  }

  def valueOf(json: JValue, field: String): String = {
    compact(render(json \ field))
  }

}
