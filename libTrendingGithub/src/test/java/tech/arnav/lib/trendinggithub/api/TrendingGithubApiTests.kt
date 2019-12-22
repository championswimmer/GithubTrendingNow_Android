package tech.arnav.lib.trendinggithub.apis

import kotlinx.coroutines.runBlocking
import org.junit.Test
import tech.arnav.lib.trendinggithub.TrendingGithub

class TrendingGithubApiTests {

    @Test
    fun get_languages() {
        runBlocking {
            val languages = TrendingGithub.api.getLanguages()

            assert(languages.isNotEmpty())
        }
    }

    @Test
    fun get_repositories() {
        runBlocking {
            val repositories = TrendingGithub.api.getRepositories()

            assert(repositories.isNotEmpty())
        }
    }

    @Test
    fun get_developers() {
        runBlocking {
            val developers = TrendingGithub.api.getDevelopers()

            assert(developers.isNotEmpty())
        }
    }

}