package readinglist

import org.scalatra.test.scalatest._
import org.scalatest.FunSuite
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.JsonMethods._

class ReadingListServletSpec extends ScalatraSuite with FunSuite {

  addServlet(classOf[ReadingListServlet], "/*")

  test("There are some books out there") {
    get("/") {
      status should equal (200)
      body should equal (compact(render("books" -> BookRepository.all.map(b => b.toHash))))
    }
  }

  test("Should get the book with id 1") {
    get("/book/1") {
      status should equal (200)
      body should equal (compact(render("book" -> BookRepository.all(0).toHash)))
    }
  }

  test("Should get the book with isbn 978-0321146533") {
    val expectedBook = Book(0, "TDD by Example", true, "978-0321146533", false)
    BookRepository.add(expectedBook)

    get("/book/isbn/978-0321146533") {
      status should equal (200)
      body should equal (compact(render("book" -> expectedBook.toHash)))
    }
  }
}
