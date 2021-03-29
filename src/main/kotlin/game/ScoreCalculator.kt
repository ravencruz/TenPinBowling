package game

import constants.BowlingConstants
import core.BowlingFrame

class ScoreCalculator(private val scoreSheet: ScoreSheet) {

    private val LAST_FRAME_INDEX = 10 - 1



    fun fillScores() {
        calculateIndividualFrameScore()

        scoreSheet.frames.forEachIndexed { index, frame ->
            frame.accumulativeScore =
                when {
//                    frame.notSpareNotStrike() -> currentFirst + currentSecond
                    frame.spare() -> {

                        frame.frameScore +
                            if (index + 1 < scoreSheet.frames.size)
                                scoreSheet.frames[index + 1].first!! //TODO remove !!
                            else 0

                    }
                    frame.strike() -> {

                        frame.frameScore + getNextTwoScoresForStrike(index)

                    }
                    else -> -1//TODO throw exception
                }
        }
    }

    private fun getNextTwoScoresForStrike(currentIndex: Int): Int {

        return if (currentIndex + 1 < scoreSheet.frames.size) {
            val nextFrame = scoreSheet.frames[currentIndex + 1]

            if (nextFrame.strike()) {
                10000
            } else {
                nextFrame.frameScore
            }

        } else {
            0
        }

        //return 0
    }

    private fun calculateIndividualFrameScore() {
        scoreSheet.frames.forEach { frame ->

            val currentFirst = frame.first ?: 0
            val currentSecond = frame.second ?: 0

            frame.frameScore =
                when {
                    frame.notSpareNotStrike() -> currentFirst + currentSecond
                    frame.spare() -> BowlingConstants.ALL_PINS_DOWN_VALUE
                    frame.strike() -> BowlingConstants.ALL_PINS_DOWN_VALUE
                    else -> -1//TODO throw exception
                }

        }
    }


}