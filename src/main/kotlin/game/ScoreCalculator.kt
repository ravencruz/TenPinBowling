package game

import constants.BowlingConstants
import core.BowlingFrame

class ScoreCalculator(private val scoreSheet: ScoreSheet) {

    private val LAST_FRAME = 10 - 1

    fun getNextFrame(currentFrame: BowlingFrame): BowlingFrame? {
        return if (currentFrame.position < scoreSheet.frames.size) {
            scoreSheet.frames[currentFrame.position]
        } else null
    }

    fun fillScores() {
        //frame score
        scoreSheet.frames.forEachIndexed { index, frame ->
            //TODO se asume q a estas alturas va a estar todo lleno, mentira porq si haces un strike el 2do se queda en null

            val currentFirst = frame.first ?: 0
            val currentSecond = frame.second ?: 0

            frame.frameScore =
                when {
                    frame.notSpareNotStrike() -> {
                        currentFirst + currentSecond

                    }
                    frame.spare() -> {
                        BowlingConstants.ALL_PINS_DOWN_VALUE

                    }
                    frame.strike() -> {
                        BowlingConstants.ALL_PINS_DOWN_VALUE

                    }
                    else -> {
                        -1//TODO throw exception
                    }
                }

        }
    }


}