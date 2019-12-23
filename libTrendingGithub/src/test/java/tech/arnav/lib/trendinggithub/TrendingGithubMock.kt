package tech.arnav.lib.trendinggithub

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import tech.arnav.lib.trendinggithub.apis.TrendingGithubAPI

object TrendingGithubMock {

    val mockWebServer = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(mockWebServer.url("/"))
        .build()

    val api: TrendingGithubAPI = retrofit.create()
}