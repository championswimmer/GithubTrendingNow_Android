package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tech.arnav.githubtrending.ui.adapters.BaseListAdapter
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModel

abstract class BaseListFragment<T> : Fragment() {

    protected var githubTrendingViewModel: GithubTrendingViewModel? = null
    lateinit var listAdapter: BaseListAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            githubTrendingViewModel = ViewModelProvider(it).get(GithubTrendingViewModel::class.java)
        }
    }

    abstract fun getAdapter(): BaseListAdapter<T>

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(getLayoutResId(), container, false)
        listAdapter = getAdapter()

        return rootView
    }

}