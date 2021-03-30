package reader

import constants.ErrorMessage
import constants.GameConstants
import exceptions.BowlingException
import game.ScoreSheet
import java.io.File
import java.lang.NumberFormatException

class BowlingFrameParser {

    fun readFrames(fileLines: List<String>): List<ScoreSheet> {

        val playerFrames = mutableMapOf<String, ScoreSheet>()

        fileLines.forEach {

            if (it.isNotBlank() && it.isNotEmpty()) {
                val tuplePlayerScore = it.split(Regex("\\s+"))
                val playerName = tuplePlayerScore[0]

                val frame = playerFrames.getOrPut(playerName) { ScoreSheet(playerName) }

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

        }

        return playerFrames.map { it.value }
    }

}