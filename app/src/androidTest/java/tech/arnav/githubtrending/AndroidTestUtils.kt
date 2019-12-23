package tech.arnav.githubtrending

import android.content.Context
import java.io.BufferedReader

/**
 * Created by championswimmer on 23/12/19.
 */

object AndroidTestUtils {
    @JvmStatic
    fun readJson(context: Context, jsonFileName: String): String {
        val jsonInputStream = context.assets.open(jsonFileName)
        val contents = jsonInputStream.bufferedReader().use(BufferedReader::readText)
        return contents
    }
}