package tech.arnav.githubtrending.ui.adapters

import android.view.View
import kotlinx.android.synthetic.main.list_item_language.view.*
import tech.arnav.lib.trendinggithub.models.Language


class LanguageListAdapter(
    layoutId: Int,
    bindView: ((itemView: View, item: Language) -> Unit)? = null
) :
    BaseListAdapter<Language>(layoutId, bindView) {

    override fun baseBindView(itemView: View, item: Language) {
        itemView.tvLangName.text = item.name
    }
}