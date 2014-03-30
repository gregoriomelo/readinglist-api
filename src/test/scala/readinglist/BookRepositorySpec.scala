package readinglist

import org.scalatra.test.scalatest._
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class BookRepositorySpec extends FunSuite with BeforeAndAfterEach with ShouldMatchers {

  override def beforeEach() {
    BookRepository.clear
  }

  test("finds existing book by id") {
    val expectedBook = Book(10, "test", false, "", true)
    BookRepository.add(expectedBook)

    BookRepository.withId(10).get should equal(expectedBook)
  }

  test("does not find missing book by id") {
    BookRepository.withId(-1) should equal(None)
  }

  test("adds a book nicely") {
    BookRepository.add(Book(1, "added book", false, "a isbn", false))

    BookRepository.size should equal (1)
  }

  test("does not add book with existing isbn") {
    BookRepository.add(Book(1, "first book with same isbn", false, "ISBN", false))
    BookRepository.add(Book(2, "second book with same ISBN", false, "ISBN", false))

    BookRepository.size should equal (1)
  }
}
