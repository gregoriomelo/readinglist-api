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
    println(BookRepository.withId({params("id").toInt}))
    BookRepository.withId({params("id").toInt}) match {
      case Some(book) => Ok(asJson(book))
      case None       => NotFound(asJson(Book.missing))
    }
  }

  get("/book/isbn/:isbn") {
    BookRepository.withIsbn({params("isbn")}) match {
      case Some(book) => Ok(asJson(book))
      case None       => NotFound(asJson(Book.missing))
    }
  }

  post("/book") {
    BookRepository.add(Book.from(request.body))

    request.body
  }

  delete("/book/:id") {
    BookRepository.withId({params("id").toInt}) match {
      case Some(removableBook) => { BookRepository.remove(removableBook); Ok(asJson(removableBook)) }
      case None                => NotFound(asJson(Book.missing))
    }
  }

  def asJson(book: Book): String = {
    "{book:" + book.toJson + "}"
  }

}
