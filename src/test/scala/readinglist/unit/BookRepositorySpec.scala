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

  test("finds existing book by isbn") {
    val expectedBook = Book(20, "test", false, "my isbn", true)
    BookRepository.add(expectedBook)

    BookRepository.withIsbn("my isbn").get should equal(expectedBook)
  }

  test("does not find missing book by isbn") {
    BookRepository.withIsbn("there should be nothing with this isbn") should equal(None)
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

  test("Deletes an existing book") {
    val book = Book(1, "first book with same isbn", false, "ISBN", false)
    BookRepository.add(book)

    BookRepository.remove(book)

    BookRepository.size should equal (0)
  }

  test("Finds all books") {
    val book = Book(1, "first book with same isbn", false, "ISBN", false)
    BookRepository.add(book)

    BookRepository.all should equal (List(book))
  }
}
