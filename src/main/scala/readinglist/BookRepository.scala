package readinglist

class BookRepository {
  val tdd = Book("TDD by Example", true, "", false)
  val rails4way = Book("The Rails 4 Way", false, "", false)
  val crucialConversations = Book("Crucial Conversations", true, "", true)

  def all = {
    List(tdd, rails4way, crucialConversations)
  }
}
