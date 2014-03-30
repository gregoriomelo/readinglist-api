package readinglist

import org.scalatra.test.scalatest._
import org.scalatest._
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.JsonMethods._

class ReadingListServletSpec extends ScalatraSuite with FunSuite with BeforeAndAfterAll {

  addServlet(classOf[ReadingListServlet], "/*")

  override def beforeAll(configMap: Map[String, Any]) {
    super.beforeAll(configMap)

    BookRepository.reset
  }

  test("There are some books out there") {
    get("/") {
      status should equal (200)
      body should equal (compact(render("books" -> BookRepository.all.map(b => b.toHash))))
    }
  }

  test("Gets the book with id 1") {
    get("/book/1") {
      status should equal (200)
      body should equal ("{book:" + BookRepository.withId(1).get.toJson + "}")
    }
  }

  test("Gets a 404 because the book does not exist") {
    get("/book/-1") {
      status should equal (404)
      body should equal ("{book:" + Book.missing.toJson + "}")
    }
  }

  test("Gets the book with isbn 978-0321146533") {
    val expectedBook = Book(1, "TDD by Example", true, "978-0321146533", false)
    BookRepository.add(expectedBook)

    get("/book/isbn/978-0321146533") {
      status should equal (200)
      body should equal ("{book:" + expectedBook.toJson + "}")
    }
  }

  test("Adds book") {
    val expectedAmountOfBooks = BookRepository.all.length + 1
    val newBookData = compact(render(("title" -> "New Book") ~ ("isRead" -> true) ~ ("isbn" -> "LONGISBN") ~ ("isStarred" -> false)))

    post("/book", newBookData) {
      status should equal (200)
      body should equal (newBookData)
      BookRepository.all.length should equal (expectedAmountOfBooks)
    }
  }

  test("Removes a book") {
    val removableBook = new Book(-1, "Remove this!", false, "REMOVE", false)
    BookRepository.add(removableBook)
    val expectedAmountOfBooks = BookRepository.all.length - 1

    delete("/book/-1") {
      status should equal (200)
      body should equal ("{book:" + removableBook.toJson + "}")
      BookRepository.all.length should equal (expectedAmountOfBooks)
    }
  }

}
