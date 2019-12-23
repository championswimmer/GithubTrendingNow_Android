package tech.arnav.githubtrending.viewmodels

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import tech.arnav.githubtrending.AndroidTestUtils
import tech.arnav.githubtrending.data.ApiResponse
import tech.arnav.lib.trendinggithub.TrendingGithubMock

/**
 * Created by championswimmer on 23/12/19.
 */
class GithubTrendingViewModelTest {

    val viewModel = GithubTrendingViewModel(TrendingGithubMock.api)
    val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun repoList_refresh_works() {
        TrendingGithubMock.mockWebServer.enqueue(
            MockResponse().setBody(
                AndroidTestUtils.readJson(context, "repositories.json")
            )
        )
        viewModel.repoList.refresh()
        assertEquals(ApiResponse.Status.LOADING, viewModel.repoList.value?.status)

        runBlocking {
            delay(1000) // wait 1 sec
            assertNotNull(viewModel.repoList.value)
            assertEquals(ApiResponse.Status.SUCCESS, viewModel.repoList.value?.status)
        }
    }

    @Test
    fun devList_refresh_works() {
        TrendingGithubMock.mockWebServer.enqueue(
            MockResponse().setBody(
                AndroidTestUtils.readJson(context, "developers.json")
            )
        )
        viewModel.devList.refresh()
        assertEquals(ApiResponse.Status.LOADING, viewModel.devList.value?.status)

        runBlocking {
            delay(1000) // wait 1 sec
            assertNotNull(viewModel.devList.value)
            assertEquals(ApiResponse.Status.SUCCESS, viewModel.devList.value?.status)
        }
    }

    @Test
    fun langList_refresh_works() {
        TrendingGithubMock.mockWebServer.enqueue(
            MockResponse().setBody(
                AndroidTestUtils.readJson(context, "languages.json")
            )
        )
        viewModel.langList.refresh()
        assertEquals(ApiResponse.Status.LOADING, viewModel.langList.value?.status)

        runBlocking {
            delay(1000) // wait 1 sec
            assertNotNull(viewModel.langList.value)
            assertEquals(ApiResponse.Status.SUCCESS, viewModel.langList.value?.status)
        }
    }

}