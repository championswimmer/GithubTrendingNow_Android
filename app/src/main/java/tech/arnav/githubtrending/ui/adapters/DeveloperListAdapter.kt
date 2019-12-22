package tech.arnav.githubtrending.ui.adapters

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_developer.view.*
import tech.arnav.lib.trendinggithub.models.Developer


class DeveloperListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Developer) -> Unit)? = null
) :
    BaseListAdapter<Developer>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Developer) {
        itemView.tvUsername.text = item.username
        itemView.tvName.text = item.name

        Glide.with(itemView.context).load(item.avatar).into(itemView.imgAvatar)
    }
}