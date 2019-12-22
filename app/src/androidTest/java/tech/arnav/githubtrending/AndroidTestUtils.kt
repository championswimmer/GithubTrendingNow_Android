package tech.arnav.githubtrending

import android.content.Context
import okio.Buffer
import tech.arnav.githubtrending.viewmodels.GithubTrendingViewModelTest

/**
 * Created by championswimmer on 23/12/19.
 */

object AndroidTestUtils {
    @JvmStatic
    fun readJson(context: Context, jsonFileName: String): Buffer {
        val byteArray = context.assets.open("repositories.json").readBytes()
        val buffer = Buffer()
        buffer.write(byteArray)
        return buffer
    }
}