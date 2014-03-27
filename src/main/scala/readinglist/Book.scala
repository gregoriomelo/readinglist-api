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
