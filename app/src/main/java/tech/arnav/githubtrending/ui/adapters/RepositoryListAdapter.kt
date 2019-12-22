package tech.arnav.githubtrending.ui.adapters

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_repository.view.*
import tech.arnav.githubtrending.R
import tech.arnav.githubtrending.ui.RepositoryDetailsActivity
import tech.arnav.lib.trendinggithub.models.Repository


class RepositoryListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Repository) -> Unit)? = null
) :
    BaseListAdapter<Repository>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Repository) {
        val repoName = SpannableString("${item.author} / ${item.name}")
        repoName.setSpan(
            TextAppearanceSpan(itemView.context, R.style.TextAppearance_MaterialComponents_Headline6),
            repoName.indexOfFirst { it == '/' },
            repoName.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        itemView.tvRepoName.text = repoName

        item.language?.let {
            val lang = SpannableString(it)

            val langBullet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                BulletSpan(4, Color.parseColor(item.languageColor), 10)
            } else {
                BulletSpan(4, Color.parseColor(item.languageColor))
            }

            lang.setSpan(
                langBullet,
                0 , 0,
                Spannable.SPAN_MARK_MARK
            )
            itemView.tvLanguage.text = lang
        }

        Glide.with(itemView).load(item.avatar).into(itemView.imgAvatar)

        itemView.cardRepo.setOnClickListener {
            val intent = Intent(itemView.context, RepositoryDetailsActivity::class.java)
            intent.putExtra(RepositoryDetailsActivity.BUNDLE_KEY_REPO, item)
            itemView.context.startActivity(intent)
        }
    }
}