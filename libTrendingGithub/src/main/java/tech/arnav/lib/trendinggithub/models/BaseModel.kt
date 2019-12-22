package tech.arnav.lib.trendinggithub.models

interface BaseModel {
    var id: String
    fun equalsId(other: BaseModel) = (this.javaClass == other.javaClass) && (this.id == other.id)
}