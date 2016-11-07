package query

import org.scalatest.FunSuite
import query.specific.{QueryFactory}

/**
  * Created by Espen on 02.11.2016.
  */
class TestFindSubjectsOfType extends FunSuite{
  test("Should be exactly 101 entities of country") {
    val subjects: List[String] = QueryFactory.findSubjectsOfType("w:Q6256")
    assert(subjects.length == 101)
    print(subjects)
  }

}