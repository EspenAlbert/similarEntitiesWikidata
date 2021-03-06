package core.globals

/**
  * Created by espen on 04.05.17.
  */
object ExternalURIs {
  val owlThing = org.apache.jena.vocabulary.OWL.Thing.getURI
  val rdfsSubclassOf= org.apache.jena.vocabulary.RDFS.subClassOf.getURI
  val datatypeInteger = """^^<http://www.w3.org/2001/XMLSchema#integer>"""
  val datatypeDouble = """^^<http://www.w3.org/2001/XMLSchema#double>"""
  val datatypeBoolean = SimilarPropertyOntology.datatypeBoolean
}
