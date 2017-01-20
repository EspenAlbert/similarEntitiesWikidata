package query.variables

/**
  * Created by Espen on 07.11.2016.
  */
import query.filters.QueryFilter

import scala.collection.mutable.ArrayBuffer
case class DynamicQueryVariable(val name : String, val distinct: Boolean, val ignoreMe : Boolean = false) extends QueryVariable with ResultQueryVariable{
  private val queryFilters : ArrayBuffer[QueryFilter] = new ArrayBuffer[QueryFilter]()
  def addQueryFilter(filter : QueryFilter) = {
    queryFilters.append(filter)
  }
  def getFilterLines: ArrayBuffer[String] = {
    val filterLines = ArrayBuffer[String]()
    for(filter <- queryFilters) {
      filterLines.append(filter.getSparql)
    }
    return filterLines
  }

  override def getSelectPhrase: String = {
    if(ignoreMe) return ""
    if(distinct) return "distinct ?" + name else return "?" + name
  }
  override def getWherePhrase: String = {
    return "?" + name
  }



}
