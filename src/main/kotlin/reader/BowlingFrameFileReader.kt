package reader

import game.ScoreSheet
import java.io.File

class BowlingFrameFileReader {

    /**
     * TODO s
     * que se de cuenta que no debe procesar las lineas vacias
     */
    fun readFramesFromFile(fileName: String): List<ScoreSheet> {

        val playerFrames = mutableMapOf<String, ScoreSheet>()

        val fileLines = readFileAsLinesUsingUseLines(fileName)
        fileLines.forEach {
            val tuplePlayerScore = it.split(Regex("\\s"))
            val playerName = tuplePlayerScore[0]

            val frame = if (playerFrames.contains(playerName)) {
                //TODO remove !!
                playerFrames[playerName]!!
            } else {

                val newFrame = ScoreSheet(playerName)
                playerFrames[playerName] = newFrame
                newFrame
            }

            //TODO constant meaning
            val score = if (tuplePlayerScore[1] == "F") 0 else tuplePlayerScore[1].toInt()
            frame.setShot(score)
        }

        return playerFrames.map { it.value }
    }

    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}