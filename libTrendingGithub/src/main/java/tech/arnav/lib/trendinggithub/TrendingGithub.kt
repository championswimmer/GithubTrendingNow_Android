package tech.arnav.lib.trendinggithub

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import tech.arnav.lib.trendinggithub.apis.TrendingGithubAPI

object TrendingGithub {

    const val API_BASE_URL = "https://github-trending-api.now.sh"

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(API_BASE_URL)
        .build()

    val api: TrendingGithubAPI = retrofit.create()
}
