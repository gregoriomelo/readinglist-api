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
      body should equal (compact(render("books" -> new BookRepository().all.map(b => b.toHash))))
    }
  }
}
