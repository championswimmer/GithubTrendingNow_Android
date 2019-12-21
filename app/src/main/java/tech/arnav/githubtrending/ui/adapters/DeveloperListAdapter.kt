package tech.arnav.githubtrending.ui.adapters

import android.view.View
import kotlinx.android.synthetic.main.list_item_developer.view.*
import tech.arnav.lib.trendinggithub.models.Developer


class DeveloperListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, T: Developer) -> Unit)? = null
) :
    BaseListAdapter<Developer>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Developer) {
        itemView.tvDevName.text = item.name
    }
}