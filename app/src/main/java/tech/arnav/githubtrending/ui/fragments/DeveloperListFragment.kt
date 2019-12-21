package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.DeveloperListAdapter
import tech.arnav.lib.trendinggithub.models.Developer

class DeveloperListFragment : BaseListFragment<Developer>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubTrendingViewModel?.devList?.observe({ lifecycle }, {
            listAdapter.submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeveloperListFragment()
    }

    override fun getAdapter() = DeveloperListAdapter(R.layout.list_item_developer)
    override fun getLayoutResId() = R.layout.fragment_repositories
}