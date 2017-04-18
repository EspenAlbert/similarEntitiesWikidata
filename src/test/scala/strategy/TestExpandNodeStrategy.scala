package strategy

import core.globals.KnowledgeGraph
import core.rdf.GraphRDF
import core.strategies.{AggregatorStrategy, ExpandNodeStrategy, StrategyFactory}
import data.WikidataFactory
import org.scalatest.FunSuite
import similarityFinder.SimilarityFinder2
import tags.ActiveSlowTag

/**
  * Created by espen on 05.04.17.
  */
class TestExpandNodeStrategy extends FunSuite{
  implicit val knowledgeGraph = KnowledgeGraph.wikidata
  val rStarr = WikidataFactory.ringoStarr
  val wd = WikidataFactory
  test("Should work for country of citizenship, it should not find many entities or timeout...", ActiveSlowTag) {
    StrategyFactory.setupStrategyFactory(List(ExpandNodeStrategy.name))
    val actualStrategiesBack = StrategyFactory.getStrategies(rStarr.id, rStarr.rdfTypes, rStarr.countryOfCitizenShipProperty, true, List(rStarr.countryOfCitizenShipValue))
    assert(actualStrategiesBack.forall(_.isInstanceOf[ExpandNodeStrategy]))
    val similarsFound = actualStrategiesBack.head.findSimilars()
    val anExpectedSimilar = wd.davidCameron
    val unexpectedSimilar = wd.johnLennon
    assert(similarsFound.contains(anExpectedSimilar))
    assert(!similarsFound.contains(unexpectedSimilar))
  }
//  test("Similarity generation for rStarr", ActiveSlowTag) {
//    StrategyFactory.setupStrategyFactory(List(AggregatorStrategy.name))
//    val simFinder = new SimilarityFinder2(rStarr.id)
//    val foundEntities = simFinder.findInitialEntitiesAsSet()
//    assert(foundEntities.size > 100)
//  }
}
