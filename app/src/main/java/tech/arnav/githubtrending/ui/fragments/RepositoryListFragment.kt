package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.RepositoryListAdapter
import tech.arnav.lib.trendinggithub.models.Repository

class RepositoryListFragment : BaseListFragment<Repository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        githubTrendingViewModel?.repoList?.observe({ lifecycle }, ::handleResponse)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepositoryListFragment()
    }

    override fun getAdapter() = RepositoryListAdapter(R.layout.list_item_repository)
    override fun getLayoutResId() = R.layout.fragment_list_base
    override fun onSwipeRefresh() = githubTrendingViewModel?.repoList?.refresh()
}