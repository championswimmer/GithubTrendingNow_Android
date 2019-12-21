package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_languages.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.LanguageListAdapter
import tech.arnav.lib.trendinggithub.models.Language

class LanguageListFragment : BaseListFragment<Language>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvLanguages.layoutManager = LinearLayoutManager(activity)
        view.rvLanguages.adapter = listAdapter

        githubTrendingViewModel?.langList?.observe({ lifecycle }, {
            listAdapter.submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageListFragment()
    }

    override fun getAdapter() = LanguageListAdapter(R.layout.list_item_language)
    override fun getLayoutResId() = R.layout.fragment_languages
}