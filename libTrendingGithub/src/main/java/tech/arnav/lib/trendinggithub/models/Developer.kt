package tech.arnav.lib.trendinggithub.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Developer(
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "repo") val repo: Repo
): Serializable, BaseModel {
    /**
     * using username as if for developers
     */
    override var id: String
        get() = username
        set(value) {}

    @JsonClass(generateAdapter = true)
    data class Repo(
        @Json(name = "name") val name: String,
        @Json(name = "description") val description: String,
        @Json(name = "url") val url: String
    ): Serializable, BaseModel {
        override var id: String
            get() = url
            set(value) {}
    }
}