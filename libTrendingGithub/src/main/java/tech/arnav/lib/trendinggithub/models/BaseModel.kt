package tech.arnav.lib.trendinggithub.models

/**
 * Marker interface for every data model to implement
 */
interface BaseModel {
    /**
     * an unique id which represents referential equality
     * if two instances of same model have same id, we will treat them as
     * referentially equal
     */
    var id: String
    fun equalsId(other: BaseModel) = (this.javaClass == other.javaClass) && (this.id == other.id)
}