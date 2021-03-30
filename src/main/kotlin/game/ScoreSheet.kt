package game

import constants.GameConstants
import core.BowlingFrame

class ScoreSheet(val player: String) {

    val frames = Array(GameConstants.FRAME_LIMIT) {
        BowlingFrame(it + 1)
    }

    fun setShot(shotScore: Int) {

        val currentFrame = getNextFrame()
        currentFrame?.let {
            when {
                currentFrame.first == null -> currentFrame.first = shotScore
                currentFrame.second == null -> currentFrame.second = shotScore
                currentFrame.third == null ->  currentFrame.third = shotScore
            }
        }
    }

    override fun toString(): String {

        var res = "$player \n"
        res += "Pinfalls\t\t"
        frames.forEach {
            res += it.getPinFallsAsString()
        }
        res += "\nScore\t\t"
        frames.forEach {
            res += it.getScoreAsString()
        }
        return res
    }

    private fun getNextFrame(): BowlingFrame? = frames.find { !it.complete() }

}