package query

import globals.{MyDatasets, SimilarPropertyOntology}
import org.scalatest.FunSuite
import query.specific.DatasetInferrer
import rdf.SimpleRDFFactory

/**
  * Created by Espen on 02.11.2016.
  */
class TestDatasetInferrer extends FunSuite{
  test("The wikidata dataset should be inferred properly") {
    val statment = SimpleRDFFactory.getStatement(("w:Q76", "w:P51", "w:QXX"))
    assert(DatasetInferrer.getDataset(statment.wherePhrase()) == MyDatasets.Wikidata)
  }
  test("The similar property dataset should be inferred properly") {
    val statment = SimpleRDFFactory.getStatement(("w:Q76", SimilarPropertyOntology.domainCount.toString, "w:QXX"))
    assert(DatasetInferrer.getDataset(statment.wherePhrase()) == MyDatasets.SimilarProperties)
  }
  test("The value match dataset should be inferred properly") {
    val statment = SimpleRDFFactory.getStatement(("w:Q76", SimilarPropertyOntology.valueMatchProp.toString, "w:QXX"))
    assert(DatasetInferrer.getDataset(statment.wherePhrase()) == MyDatasets.ValueMatch)
  }


}