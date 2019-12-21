package tech.arnav.lib.trendinggithub.apis

import retrofit2.Response
import retrofit2.http.GET
import tech.arnav.lib.trendinggithub.models.Developer
import tech.arnav.lib.trendinggithub.models.Language
import tech.arnav.lib.trendinggithub.models.Repository

interface TrendingGithubAPI {

    @GET("languages")
    suspend fun getLanguages(): List<Language>

    @GET("repositories")
    suspend fun getRepositories(): List<Repository>

    @GET("developers")
    suspend fun getDevelopers(): List<Developer>
}