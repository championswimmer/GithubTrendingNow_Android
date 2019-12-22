package tech.arnav.githubtrending.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_repository_details.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.adapters.AuthorListAdapter
import tech.arnav.githubtrending.ui.textspannables.SpannableLanguage
import tech.arnav.githubtrending.ui.textspannables.SpannableRepoName
import tech.arnav.lib.trendinggithub.models.Repository

class RepositoryDetailsActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_KEY_REPO = "key_repo"
    }

    var repository: Repository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        (intent.extras?.getSerializable(BUNDLE_KEY_REPO) as? Repository)?.let {
            repository = it
        } ?: run {
            Toast.makeText(
                this@RepositoryDetailsActivity,
                "Invalid repository details",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        supportActionBar?.apply{
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        with(repository!!) {
            tvRepoName.text =
                SpannableRepoName(this@RepositoryDetailsActivity, "$author / $name")
            tvDescription.text = description

            language?.let {
                tvLanguage.text = SpannableLanguage(it, languageColor)
            }

            Glide.with(this@RepositoryDetailsActivity).load(avatar).into(imgAvatar)

            rvAuthors.layoutManager = LinearLayoutManager(this@RepositoryDetailsActivity)
            rvAuthors.adapter = AuthorListAdapter(R.layout.list_item_developer).also {
                it.submitList(builtBy)
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            (android.R.id.home) ->  finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
