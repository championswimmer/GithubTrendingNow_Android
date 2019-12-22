package tech.arnav.githubtrending.ui.textspannables

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import tech.arnav.githubtrending.R

class SpannableRepoName(context: Context, repoName: String) : SpannableString(repoName) {
    init {
        setSpan(
            TextAppearanceSpan(context, R.style.TextAppearance_MaterialComponents_Headline6),
            repoName.indexOfFirst { it == '/' },
            repoName.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }
}