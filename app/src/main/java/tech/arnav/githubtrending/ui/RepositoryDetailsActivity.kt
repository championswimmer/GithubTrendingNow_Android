package tech.arnav.githubtrending.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.arnav.githubtrending.R
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
        } ?: finish()

    }
}
