package tech.arnav.githubtrending.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.fragments.DeveloperListFragment
import tech.arnav.githubtrending.ui.fragments.LanguageListFragment
import tech.arnav.githubtrending.ui.fragments.RepositoryListFragment
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModel

class GithubTrendingActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var githubTrendingViewModel: GithubTrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubTrendingViewModel = ViewModelProvider(this).get(GithubTrendingViewModel::class.java)

        githubTrendingViewModel.refreshRepos()
        githubTrendingViewModel.refreshDevs()
        githubTrendingViewModel.refreshLangs()


        supportFragmentManager.commit {
            replace(R.id.main_container, RepositoryListFragment.newInstance())
        }

        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_repositories -> RepositoryListFragment.newInstance()
            R.id.menu_developers -> DeveloperListFragment.newInstance()
            R.id.menu_languages -> LanguageListFragment.newInstance()
            else -> null
        }?.let {

            supportFragmentManager.commit {
                replace(R.id.main_container, it)
            }

            true
        } ?: false
    }


}
