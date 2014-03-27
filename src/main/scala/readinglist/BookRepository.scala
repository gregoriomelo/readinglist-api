package readinglist

object BookRepository {
  val tdd = Book(1, "TDD by Example", true, "", false)
  val rails4way = Book(2, "The Rails 4 Way", false, "", false)
  val crucialConversations = Book(3, "Crucial Conversations", true, "", true)

  var books = List(tdd, rails4way, crucialConversations)

  def all = { books }

  def add(newBook: Book) = {
    books :+= newBook
  }

  def withIsbn(isbn: String) = {
    books.find(book => book.isbn == isbn).getOrElse(new Book(0, "", false, "", false))
  }
}
