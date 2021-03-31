package reader

import game.ScoreSheet

class BowlingFrameParser {

    fun readFrames(fileLines: List<String>): List<ScoreSheet> {

        val playerFrames = mutableMapOf<String, ScoreSheet>()

        fileLines.forEach {
            if (hasData(it)) {
                val tuplePlayerScore = parsePlayerScore(it)
                val frame = playerFrames.getOrPut(tuplePlayerScore.player) { ScoreSheet(tuplePlayerScore.player) }
                val ballRoll = getBallRoll(tuplePlayerScore)
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

    private fun hasData(inputLine : String) = inputLine.isNotBlank() && inputLine.isNotEmpty()



}