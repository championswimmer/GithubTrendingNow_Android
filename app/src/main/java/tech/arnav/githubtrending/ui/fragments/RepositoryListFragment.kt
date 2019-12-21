package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_repositories.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.RepositoryListAdapter
import tech.arnav.lib.trendinggithub.models.Repository

class RepositoryListFragment : BaseListFragment<Repository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvRepositories.layoutManager = LinearLayoutManager(activity)
        view.rvRepositories.adapter = listAdapter

        githubTrendingViewModel?.repoList?.observe({ lifecycle }, {
            listAdapter.submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepositoryListFragment()
    }

    override fun getAdapter() = RepositoryListAdapter(R.layout.list_item_repository)
    override fun getLayoutResId() = R.layout.fragment_repositories
}