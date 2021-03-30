package reader

import java.io.File

object BowlingFileReader {

    fun readFileAsLinesUsingUseLines(fileName: String): List<String> {

        val resourceFile = javaClass.getResource(fileName).file
        return File(resourceFile).useLines { it.toList() }
    }

    fun readFileAsLinesUsingBufferedReader(fullPath: String): List<String> {

        return File(fullPath).bufferedReader().readLines()
    }


}