package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.LanguageListAdapter
import tech.arnav.lib.trendinggithub.models.Language

class LanguageListFragment : BaseListFragment<Language>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        githubTrendingViewModel?.langList?.observe({ lifecycle }, ::handleResponse)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageListFragment()
    }

    override fun getAdapter() = LanguageListAdapter(R.layout.list_item_language)
    override fun getLayoutResId() = R.layout.fragment_list_base
    override fun onSwipeRefresh() = githubTrendingViewModel?.langList?.refresh()
}