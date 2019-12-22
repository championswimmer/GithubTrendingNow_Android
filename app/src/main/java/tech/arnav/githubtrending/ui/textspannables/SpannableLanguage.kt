package tech.arnav.githubtrending.ui.textspannables

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan

class SpannableLanguage(langName: String, langColor: String?): SpannableString(langName) {
    init {
        val langBullet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            BulletSpan(4, Color.parseColor(langColor), 10)
        } else {
            BulletSpan(4, Color.parseColor(langColor))
        }

        setSpan(
            langBullet,
            0 , 0,
            Spannable.SPAN_MARK_MARK
        )
    }
}