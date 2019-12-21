package tech.arnav.githubtrending.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.arnav.lib.trendinggithub.TrendingGithub
import tech.arnav.lib.trendinggithub.models.Developer
import tech.arnav.lib.trendinggithub.models.Language
import tech.arnav.lib.trendinggithub.models.Repository

class GithubTrendingViewModel: ViewModel() {
    // ============ Live Data ===============
    val repoList = MutableLiveData<List<Repository>>()
    val langList = MutableLiveData<List<Language>>()
    val devList = MutableLiveData<List<Developer>>()

    // ============ Sync Methods =============

    fun refreshRepos() = viewModelScope.launch {
        repoList.postValue(TrendingGithub.api.getRepositories())
    }

    fun refreshLangs() = viewModelScope.launch {
        langList.postValue(TrendingGithub.api.getLanguages())
    }

    fun refreshDevs() = viewModelScope.launch {
        devList.postValue(TrendingGithub.api.getDevelopers())
    }



}