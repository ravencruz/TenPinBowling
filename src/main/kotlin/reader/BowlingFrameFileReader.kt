package reader

import constants.ErrorMessage
import constants.GameConstants
import exceptions.BowlingException
import game.ScoreSheet
import java.io.File
import java.lang.NumberFormatException

class BowlingFrameFileReader {

    /**
     * TODO s
     * que se de cuenta que no debe procesar las lineas vacias
     */
    fun readFramesFromFile(fileName: String): List<ScoreSheet> {

        val playerFrames = mutableMapOf<String, ScoreSheet>()

        val fileLines = readFileAsLinesUsingUseLines(fileName)
        fileLines.forEach {
            val tuplePlayerScore = it.split(Regex("\\s+"))
            val playerName = tuplePlayerScore[0]

            val frame = if (playerFrames.contains(playerName)) {
                //TODO remove !!
                playerFrames[playerName]!!
            } else {

                val newFrame = ScoreSheet(playerName)
                playerFrames[playerName] = newFrame
                newFrame
            }

            val ballRoll =
            try {
                 if (tuplePlayerScore[1] == GameConstants.FRAME_SCORE_FOUL) 0 else tuplePlayerScore[1].toInt()
            } catch (nfe: NumberFormatException) {
                throw BowlingException(ErrorMessage.INVALID_SCORE_VALUE)
            }

            if (ballRoll > 10 || ballRoll < 0) {
                throw BowlingException(ErrorMessage.INVALID_SCORE_VALUE)
            }

            frame.setShot(ballRoll)
        }

        return playerFrames.map { it.value }
    }

    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}