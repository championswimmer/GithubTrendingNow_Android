package tech.arnav.githubtrending.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_repositories.view.*
import kotlinx.android.synthetic.main.list_item_repository.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.RepositoryListAdapter
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModel
import tech.arnav.lib.trendinggithub.models.Repository

class RepositoriesFragment : Fragment() {

    var githubTrendingViewModel: GithubTrendingViewModel? = null
    lateinit var repoAdapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            githubTrendingViewModel = ViewModelProvider(it).get(GithubTrendingViewModel::class.java)
            githubTrendingViewModel?.refreshRepos()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repositories, container, false)
        repoAdapter = RepositoryListAdapter(
            R.layout.list_item_repository
        ) { itemView: View, repo: Repository ->
            itemView.tvRepoName.text = repo.name
        }
        rootView.rvRepositories.layoutManager = LinearLayoutManager(activity)
        rootView.rvRepositories.adapter = repoAdapter

        githubTrendingViewModel?.repoList?.observe({ lifecycle }, {
            repoAdapter.submitList(it)
        })


        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepositoriesFragment()
    }
}