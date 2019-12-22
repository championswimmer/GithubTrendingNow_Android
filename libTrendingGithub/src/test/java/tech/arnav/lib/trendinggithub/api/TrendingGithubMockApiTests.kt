package tech.arnav.lib.trendinggithub.apis

import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import tech.arnav.lib.trendinggithub.TestUtils
import tech.arnav.lib.trendinggithub.TrendingGithubMock
import java.util.concurrent.TimeUnit

class TrendingGithubMockApiTests {


    @Test
    fun `GET languages`() {
        runBlocking {
            val langResp = TestUtils.readJson("responses/languages.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(langResp))

            val languages = TrendingGithubMock.api.getLanguages()

            assertNotNull(languages)
            assertEquals("1c-enterprise", languages[0].urlParam)
            assertEquals(471, languages.size)
        }
    }

    @Test
    fun `GET repositories`() {
        runBlocking {
            val repoResp = TestUtils.readJson("responses/repositories.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(repoResp))
            val repositories = TrendingGithubMock.api.getRepositories()

            assert(repositories.isNotEmpty())
        }
    }

    @Test
    fun `GET developers`() {
        runBlocking {
            val devResp = TestUtils.readJson("responses/developers.json")
            TrendingGithubMock.mockWebServer.enqueue(MockResponse().setBody(devResp))
            val developers = TrendingGithubMock.api.getDevelopers()

            assert(developers.isNotEmpty())
        }
    }

}