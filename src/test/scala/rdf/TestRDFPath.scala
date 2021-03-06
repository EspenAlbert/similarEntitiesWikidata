package rdf

import core.rdf.RDFPath
import core.testData.WikidataFactory
import org.scalatest.FunSuite

/**
  * Created by espen on 02.05.17.
  */
class TestRDFPath extends FunSuite{
  test("should print correct path") {
    val wd = WikidataFactory
    val l1FoundE = wd.ringoStarr.spouseValues.head
    val spouseProp = wd.ringoStarr.spouseProp
    val rs = wd.ringoStarr.id
    val expected = s"${rs},true,${spouseProp},${l1FoundE}"
    val actual = RDFPath(rs, l1FoundE, spouseProp :: Nil, true::Nil, Nil)
    assert(expected == actual.toString)
    val memberProp = wd.ringoStarr.memberOfProp
    val beatles = wd.ringoStarr.memberOfValue
    val jl = wd.johnLennon
    val expectedL2 = s"$rs,true,${memberProp},${beatles},false,${memberProp},${jl}"
    val actualL2 = RDFPath(rs,jl, memberProp::memberProp::Nil, true::false::Nil,beatles::Nil)
    assert(expectedL2 == actualL2.toString)
  }
  test("Extending a path should work"){
    val wd = WikidataFactory
    val memberProp = wd.ringoStarr.memberOfProp
    val rs = wd.ringoStarr.id
    val jl = wd.johnLennon
    val beatles = wd.ringoStarr.memberOfValue

    val l1 = RDFPath(rs,beatles,memberProp::Nil,true::Nil,Nil)
    val l2 = RDFPath.extend(l1, beatles, memberProp, jl)
    val expectdL2 = RDFPath(rs,jl, memberProp::memberProp::Nil, true::false::Nil,beatles::Nil)
    assert(l2 == expectdL2)

  }

}
