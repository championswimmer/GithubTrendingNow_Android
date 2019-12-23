package tech.arnav.lib.trendinggithub.apis

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import tech.arnav.lib.trendinggithub.TestUtils
import tech.arnav.lib.trendinggithub.TrendingGithubMock

class TrendingGithubMockApiTests {

    @Test
    fun get_languages() {
        if (TestUtils.isAndroid()) {
            return // only for JVM
        }
        runBlocking {
            val langResp = TestUtils.readJson("assets/languages.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(langResp))

            val languages = TrendingGithubMock.api.getLanguages()

            assertNotNull(languages)
            assertEquals("1c-enterprise", languages[0].urlParam)
            assertEquals(471, languages.size)
        }
    }

    @Test
    fun get_repositories() {
        if (TestUtils.isAndroid()) {
            return // only for JVM
        }
        runBlocking {
            val repoResp = TestUtils.readJson("assets/repositories.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(repoResp))
            val repositories = TrendingGithubMock.api.getRepositories()

            assert(repositories.isNotEmpty())
        }
    }

    @Test
    fun get_developers() {
        if (TestUtils.isAndroid()) {
            return // only for JVM
        }
        runBlocking {
            val devResp = TestUtils.readJson("assets/developers.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(devResp))
            val developers = TrendingGithubMock.api.getDevelopers()

            assert(developers.isNotEmpty())
        }
    }

}