package tech.arnav.githubtrending.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.arnav.githubtrending.data.LiveApiResponse
import tech.arnav.lib.trendinggithub.TrendingGithub
import tech.arnav.lib.trendinggithub.models.Developer
import tech.arnav.lib.trendinggithub.models.Language
import tech.arnav.lib.trendinggithub.models.Repository

class GithubTrendingViewModel: ViewModel() {
    // ============ Live Data ===============
    val repoList = LiveApiResponse(viewModelScope, TrendingGithub.api::getRepositories)
    val langList = LiveApiResponse(viewModelScope, TrendingGithub.api::getLanguages)
    val devList = LiveApiResponse(viewModelScope, TrendingGithub.api::getDevelopers)


}