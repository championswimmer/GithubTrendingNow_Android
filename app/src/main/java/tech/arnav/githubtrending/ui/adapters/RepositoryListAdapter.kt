package tech.arnav.githubtrending.ui.adapters

import android.view.View
import tech.arnav.lib.trendinggithub.models.Repository


class RepositoryListAdapter(layoutId: Int, bindView: (itemView: View, T: Repository) -> Unit) :
    BaseListAdapter<Repository>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Repository) {

    }
}