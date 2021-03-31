package reader

import constants.ErrorMessage
import constants.GameConstants
import exceptions.BowlingException
import game.ScoreSheet

class BowlingFrameParser {

    fun readFrames(fileLines: List<String>): List<ScoreSheet> {

        val playerFrames = mutableMapOf<String, ScoreSheet>()

        fileLines.forEach {
            if (hasData(it)) {
                val tuplePlayerScore = parsePlayerScore(it)
                val frame = playerFrames.getOrPut(tuplePlayerScore.player) { ScoreSheet(tuplePlayerScore.player) }

                val ballRoll = getBallRoll(tuplePlayerScore)
                //if (invalidScoreValue(ballRoll)) throw BowlingException(ErrorMessage.INVALID_SCORE_VALUE)
                frame.setShot(ballRoll)
            }
        }

        return playerFrames.map { it.value }
    }

    private fun parsePlayerScore(line: String): PlayerScoreTuple {
        val tuplePlayerScore = line.split(Regex("\\s+"))
        return PlayerScoreTuple(tuplePlayerScore[0], tuplePlayerScore[1])
    }

    private fun getBallRoll(tuplePlayerScore: PlayerScoreTuple) =
        tuplePlayerScore.score
//        try {
//            if (tuplePlayerScore.score == GameConstants.FRAME_SCORE_FOUL) 0 else tuplePlayerScore.score.toInt()
//        } catch (nfe: NumberFormatException) {
//            throw BowlingException(ErrorMessage.INVALID_SCORE_VALUE)
//        }

    private fun hasData(inputLine : String) = inputLine.isNotBlank() && inputLine.isNotEmpty()

    private fun invalidScoreValue(scoreValue: Int) = scoreValue > 10 || scoreValue < 0

}