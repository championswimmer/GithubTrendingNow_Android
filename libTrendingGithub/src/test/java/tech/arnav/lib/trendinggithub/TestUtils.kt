package tech.arnav.lib.trendinggithub

import java.io.File

object TestUtils {

    fun readJson(path: String): String {
        val file = File("src/test/" + path)
        return String(file.readBytes())
    }
}