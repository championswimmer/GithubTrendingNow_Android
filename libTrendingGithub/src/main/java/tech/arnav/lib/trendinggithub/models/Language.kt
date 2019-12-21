package tech.arnav.lib.trendinggithub.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Language(
    @Json(name = "urlParam") val urlParam: String,
    @Json(name = "name") val name: String
) : Serializable