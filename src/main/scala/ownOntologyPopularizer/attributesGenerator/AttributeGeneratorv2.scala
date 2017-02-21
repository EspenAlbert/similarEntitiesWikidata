package ownOntologyPopularizer.attributesGenerator

import globals._
import query.AskQuery
import query.specific.{QueryFactoryRaw, UpdateQueryFactory}

import scala.collection.mutable

/**
  * Created by espen on 20.02.17.
  */
object AttributeGeneratorv2 {
  def generateMetaStatementKnowledgeAndStrategiesForProperties(propertiesToPropTypeMap : Map[String, PropertyType]) = {

    val (propToDomainCount: mutable.Map[String, Int], propToRangeCount: mutable.Map[String, Int], sameTypePossibleProps: mutable.Set[String], sharableDomainProps: mutable.Set[String], sharableRangeProps: mutable.Set[String], dateTimeStrategies : mutable.Set[String]) = findMetaPropertyKnowledge(propertiesToPropTypeMap)
    addMetaKnowledgeToDatabase(propToDomainCount, propToRangeCount)
    addStrategiesToDatabase(propertiesToPropTypeMap, sameTypePossibleProps, sharableDomainProps, sharableRangeProps, dateTimeStrategies)
  }

  def addStrategiesToDatabase(propertiesToPropTypeMap: Map[String, PropertyType], sameTypePossibleProps: mutable.Set[String], sharableDomainProps: mutable.Set[String], sharableRangeProps: mutable.Set[String], dateTimeStrategies : mutable.Set[String]) = {
    //Add strategies to ontology
    UpdateQueryFactory.addStatements(propertiesToPropTypeMap.map { case (key, value) => s"<$key> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.propertyMatchStrategy}> ." }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(sameTypePossibleProps.map { (v) => s"<$v> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.alternativeLinkStrategy}> ." }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(sameTypePossibleProps.map { (v) => s"<$v> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.directLinkStrategy}> ." }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(sharableDomainProps.map { (prop) => s"<$prop> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.valueMatchSubjectStrategy}> ." }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(sharableRangeProps.map { (prop) => s"<$prop> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.valueMatchObjectStrategy}> ." }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(dateTimeStrategies.map { (prop) => s"<$prop> <${SimilarPropertyOntology.rdfType}> <${SimilarPropertyOntology.dateTimeStrategy}> ." }, MyDatasets.similarProperties2)
  }

  def addMetaKnowledgeToDatabase(propToDomainCount: mutable.Map[String, Int], propToRangeCount: mutable.Map[String, Int]) = {
    //Add meta-property knowledge to ontology
    UpdateQueryFactory.addStatements(propToDomainCount.map { case (key, value) => s"""<$key> <${SimilarPropertyOntology.domainCount}> "$value" .""" }, MyDatasets.similarProperties2)
    UpdateQueryFactory.addStatements(propToRangeCount.map { case (key, value) => s"""<$key> <${SimilarPropertyOntology.rangeCount}> "$value" .""" }, MyDatasets.similarProperties2)
  }

  def findMetaPropertyKnowledge(propertiesToPropTypeMap: Map[String, PropertyType]) = {
    val propToDomainCount = mutable.Map[String, Int]()
    val propToRangeCount = mutable.Map[String, Int]()
    val sameTypePossibleProps = mutable.Set[String]()
    val sharableDomainProps = mutable.Set[String]()
    val sharableRangeProps = mutable.Set[String]()
    val dateProperties = mutable.Set[String]()
    for ((prop, propType) <- propertiesToPropTypeMap) {
      propToDomainCount += (prop -> QueryFactoryRaw.findDomainCount(prop))
      propType match {
        case a: ItemPropertyType => {
          propToRangeCount += (prop -> QueryFactoryRaw.findRangeCount(prop))
          if (AskQuery.sameTypePossibleForProp(prop)) sameTypePossibleProps += prop
          if (AskQuery.sharableDomain(prop)) sharableDomainProps += prop
          if (AskQuery.sharableRange(prop)) sharableRangeProps += prop
        }
        case a: DateTimePropertyType => dateProperties += prop
        case _ =>
      }

    }
    (propToDomainCount, propToRangeCount, sameTypePossibleProps, sharableDomainProps, sharableRangeProps, dateProperties)
  }
}
