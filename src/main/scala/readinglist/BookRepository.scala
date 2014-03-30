package readinglist

object BookRepository {
  val tdd = Book(1, "TDD by Example", true, "978-0321146533", false)
  val rails4way = Book(2, "The Rails 4 Way", false, "978-0321944276", false)
  val crucialConversations = Book(3, "Crucial Conversations", true, "978-0071771320", true)

  var books = List(tdd, rails4way, crucialConversations)

  def all = { books }

  def add(newBook: Book) = {
    withIsbn(newBook.isbn) match {
      case None => books :+= newBook
      case _ => ;
    }
  }

  def withId(id: Long) = {
    books.find(book => book.id == id)
  }

  def withIsbn(isbn: String) = {
    books.find(book => book.isbn == isbn)
  }

  def remove(book: Book) {
    books = books diff List(book)
  }

  def size = {
    books.length
  }

  def clear {
    books = List[Book]()
  }

  def reset {
    books = List(tdd, rails4way, crucialConversations)
  }
}
