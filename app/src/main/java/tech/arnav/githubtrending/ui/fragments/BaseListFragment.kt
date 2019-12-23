package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_list_base.view.*
import tech.arnav.githubtrending.data.ApiResponse
import tech.arnav.githubtrending.ui.adapters.BaseListAdapter
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModel
import tech.arnav.lib.trendinggithub.models.BaseModel

abstract class BaseListFragment<T: BaseModel> : Fragment() {

    protected var githubTrendingViewModel: GithubTrendingViewModel? = null
    lateinit var listAdapter: BaseListAdapter<T>
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        swipeRefreshLayout = rootView.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener{ onSwipeRefresh() }
        listAdapter = getAdapter()
        rootView.recyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.recyclerView.adapter = listAdapter

        return rootView
    }

    abstract fun onSwipeRefresh(): Unit?

    fun handleResponse(response: ApiResponse<List<T>>) {
        when (response.status) {
            ApiResponse.Status.NONE -> {
                swipeRefreshLayout.isRefreshing = false
            }
            ApiResponse.Status.LOADING -> {
                swipeRefreshLayout.isRefreshing = true
            }
            ApiResponse.Status.SUCCESS -> {
                swipeRefreshLayout.isRefreshing = false
                listAdapter.submitList(response.get())
            }
            ApiResponse.Status.FAILURE -> {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(activity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}