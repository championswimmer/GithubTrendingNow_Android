package tech.arnav.githubtrending.ui.adapters

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_repository.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.RepositoryDetailsActivity
import tech.arnav.githubtrending.ui.textspannables.SpannableLanguage
import tech.arnav.githubtrending.ui.textspannables.SpannableRepoName
import tech.arnav.lib.trendinggithub.models.Repository


class RepositoryListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Repository) -> Unit)? = null
) :
    BaseListAdapter<Repository>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Repository) {
        itemView.tvRepoName.text =
            SpannableRepoName(itemView.context, "${item.author} / ${item.name}")

        item.language?.let {
            itemView.tvLanguage.text = SpannableLanguage(it, item.languageColor)
        }

        Glide.with(itemView)
            .load(item.avatar)
            .placeholder(R.drawable.ic_placeholder_repo_avatar)
            .into(itemView.imgAvatar)

        itemView.cardRepo.setOnClickListener {
            val intent = Intent(itemView.context, RepositoryDetailsActivity::class.java)
            intent.putExtra(RepositoryDetailsActivity.BUNDLE_KEY_REPO, item)
            itemView.context.startActivity(intent)
        }
    }
}