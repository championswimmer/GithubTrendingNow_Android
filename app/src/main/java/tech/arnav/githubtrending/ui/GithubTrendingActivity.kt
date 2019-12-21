package tech.arnav.githubtrending.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.fragments.RepositoriesFragment
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModel

class GithubTrendingActivity : AppCompatActivity() {

    lateinit var githubTrendingViewModel: GithubTrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        githubTrendingViewModel = ViewModelProvider(this).get(GithubTrendingViewModel::class.java)


        supportFragmentManager.commit {
            replace(R.id.main_container, RepositoriesFragment.newInstance())
        }
    }
}
