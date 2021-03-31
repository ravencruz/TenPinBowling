package game

import constants.GameConstants
import core.BowlingFrame

class ScoreSheet(val player: String) {

    val frames = Array(GameConstants.FRAME_LIMIT) {
        BowlingFrame(it + 1)
    }

    fun setShot(shotScore: String) {

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

    fun getFrameScore(frame: Int): Int {
        return frames[frame - 1].frameScore
    }

    fun getAccumulativeScore(frame: Int): Int {
        return frames[frame - 1].accumulativeScore
    }

    fun isComplete(): Boolean {
        return frames.all { it.complete() }
    }

}