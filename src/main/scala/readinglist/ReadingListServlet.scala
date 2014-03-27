package readinglist

import org.scalatra._
import scalate.ScalateSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.JsonMethods._

class ReadingListServlet extends ReadinglistStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    compact(render("books" -> BookRepository.all.map(b => b.toHash)))
  }

  get("/book/:id") {
    compact(render("book" -> BookRepository.all({params("id").toInt - 1}).toHash))
  }

  get("/book/isbn/:isbn") {
    compact(render("book" -> BookRepository.withIsbn({params("isbn")}).toHash))
  }

  post("/book") {
    BookRepository.add(Book.from(request.body))

    request.body
  }

}
