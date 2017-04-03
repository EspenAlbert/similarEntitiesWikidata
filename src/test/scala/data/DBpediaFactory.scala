package data

/**
  * Created by espen on 28.03.17.
  */
import WikidataFactory.w
import core.globals._
object DBpediaFactory {

  val propToTypeFilename = "DBpedia-propToTypeMapping"
  val resource = "http://dbpedia.org/resource/"
  val ontology = "http://dbpedia.org/ontology/"
  val standardRdfType = "http://www.w3.org/2004/02/skos/core#Concept"

  val obama: String = resource + "Barack_Obama"

  val wales = new {
    val wikidataId = w + "Q25"
    val dbpediaId = resource + "Wales"
  }
  val ringoStarr = new {
    val id = resource + "Ringo_Starr"
    val formerBandMemberProp = "http://dbpedia.org/ontology/formerBandMember"
    val formerBandMemberSubject ="http://dbpedia.org/resource/The_Beatles"
    val genreProp = ontology + "genre"
    val genreValues = List("http://dbpedia.org/resource/Pop_music", "http://dbpedia.org/resource/Rock_music")
    val dctSubjectProp = "http://purl.org/dc/terms/subject"
    val dctSubjectSomeOfTheValues = List("http://dbpedia.org/resource/Category:E1_Music_artists", "http://dbpedia.org/resource/Category:Musicians_from_Liverpool", "http://dbpedia.org/resource/Category:Living_people")
    val spouseProp = "http://dbpedia.org/ontology/spouse"
    val spouseValues = List("http://dbpedia.org/resource/Maureen_Starkey_Tigrett", "http://dbpedia.org/resource/Barbara_Bach")
    val rdfTypeProp = SimilarPropertyOntology.rdfType
    val rdfTypeValues = List(standardRdfType, "http://dbpedia.org/ontology/MusicalArtist")
    val activeYearsProp = "http://dbpedia.org/ontology/activeYearsStartYear"
    val activeYearsValue = "1957-01-01"
    val websiteProp = "http://dbpedia.org/property/website"
    val websiteValue = "http://www.ringostarr.com/"
//    val yearsActiveProp = "http://dbpedia.org/property/yearsActive"
//    val yearsActiveValue = 1957 DOesn't exist...
  }
  val latitudeProperty = "http://www.w3.org/2003/01/geo/wgs84_pos#lat"
  val longitudeProperty = "http://www.w3.org/2003/01/geo/wgs84_pos#long"
  val directLinkProperty = ringoStarr.spouseProp
  val valueMatchObjectProperty = ringoStarr.dctSubjectProp
  val valueMatchSubjectProperty = ringoStarr.formerBandMemberProp
  val dateProperty = ringoStarr.activeYearsProp
  val quantityProperty = "http://dbpedia.org/ontology/carbohydrate"
  val quantityPropertyDomainCount = 2
  val sameTypesPossible = List(directLinkProperty)
  val rangeCountsProps = List(directLinkProperty, valueMatchSubjectProperty, valueMatchObjectProperty)
  val sharableDomain = List(directLinkProperty, valueMatchSubjectProperty, valueMatchObjectProperty)
  val expectedPropertyMapping = Map[String, PropertyType](
    directLinkProperty -> ItemPropertyType(),
    valueMatchObjectProperty -> ItemPropertyType(),
    valueMatchSubjectProperty-> ItemPropertyType(),
    ringoStarr.websiteProp -> UrlPropertyType(),
    dateProperty -> DateTimePropertyType(),
    quantityProperty -> QuantityPropertyType(),
    latitudeProperty -> GlobeCoordinatePropertyType(),
    longitudeProperty -> GlobeCoordinatePropertyType()
  )

}