package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_developers.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.DeveloperListAdapter
import tech.arnav.lib.trendinggithub.models.Developer

class DeveloperListFragment : BaseListFragment<Developer>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvDevelopers.layoutManager = LinearLayoutManager(activity)
        view.rvDevelopers.adapter = listAdapter

        githubTrendingViewModel?.devList?.observe({ lifecycle }, {
            listAdapter.submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeveloperListFragment()
    }

    override fun getAdapter() = DeveloperListAdapter(R.layout.list_item_developer)
    override fun getLayoutResId() = R.layout.fragment_developers
}