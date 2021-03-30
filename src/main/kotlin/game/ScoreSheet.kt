package game

import constants.GameConstants
import core.BowlingFrame

class ScoreSheet(val player: String) {

    val frames = Array(GameConstants.FRAME_LIMIT) {
        BowlingFrame(it + 1)
    }

    fun setShot(shotScore: Int) {

        val currentFrame = getNextFrame()

        //TODO fix != null
        if (currentFrame != null) {
            //TODO fix != null
            if (currentFrame.first == null) {
                currentFrame.first = shotScore

                //TODO fix != null
            } else if (currentFrame.second == null) {
                currentFrame.second = shotScore

                //TODO fix != null
            } else if (currentFrame.third == null) {
                currentFrame.third = shotScore
            }
        }
    }


    fun getFrameScore(frame: Int): Int {
        return frames[frame - 1].frameScore
    }

    fun getAccumulativeScore(frame: Int): Int {
        return frames[frame - 1].accumulativeScore
    }


    override fun toString(): String {

        var res = "$player \n"
        res += "Pinfalls\t\t"
        frames.forEach {
            res += it.getPinFalls()
        }
        res += "\nScore\t\t"
        frames.forEach {
            res += it.getScoreAsString()
        }
        return res
    }

    private fun getNextFrame(): BowlingFrame? = frames.find { !it.complete() }


}