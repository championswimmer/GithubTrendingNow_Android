package tech.arnav.githubtrending.ui.adapters

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_developer.view.*
import tech.arnav.lib.trendinggithub.models.Repository.Author


class AuthorListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Author) -> Unit)? = null
) :
    BaseListAdapter<Author>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Author) {
        itemView.tvUsername.text = item.username
        itemView.tvName.visibility = View.GONE

        Glide.with(itemView.context).load(item.avatar).into(itemView.imgAvatar)
    }
}