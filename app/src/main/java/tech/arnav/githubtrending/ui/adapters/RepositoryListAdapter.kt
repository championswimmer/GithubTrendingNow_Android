package tech.arnav.githubtrending.ui.adapters

import android.view.View
import kotlinx.android.synthetic.main.list_item_repository.view.*
import tech.arnav.lib.trendinggithub.models.Repository


class RepositoryListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Repository) -> Unit)? = null
) :
    BaseListAdapter<Repository>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Repository) {
        itemView.tvRepoName.text = item.name
    }
}