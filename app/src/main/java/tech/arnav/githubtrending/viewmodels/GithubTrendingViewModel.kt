package tech.arnav.githubtrending.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tech.arnav.githubtrending.data.LiveApiResponse
import tech.arnav.lib.trendinggithub.TrendingGithub
import tech.arnav.lib.trendinggithub.apis.TrendingGithubAPI

/**
 * ViewModel to store lists of repositories, developers and languages
 *
 * @param apiService an instance of [TrendingGithubAPI], defaulting to [TrendingGithub.api]
 *          but can be injected with a mock implementation for testing
 *
 * @author championswimmer
 */
class GithubTrendingViewModel(
    apiService: TrendingGithubAPI = TrendingGithub.api
) : ViewModel() {

    // ============ Live Data ===============
    val repoList = LiveApiResponse(viewModelScope, apiService::getRepositories)
    val langList = LiveApiResponse(viewModelScope, apiService::getLanguages)
    val devList = LiveApiResponse(viewModelScope, apiService::getDevelopers)


}