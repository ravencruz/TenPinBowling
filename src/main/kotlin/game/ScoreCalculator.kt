package game

import constants.BowlingConstants
import core.BowlingFrame

class ScoreCalculator(private val scoreSheet: ScoreSheet) {

    private val LAST_FRAME_INDEX = 10 - 1



    fun fillScores() {
        calculateIndividualFrameScore()

        scoreSheet.frames.forEachIndexed { index, frame ->

            val previous = if (index-1 >= 0) scoreSheet.frames[index-1].accumulativeScore else 0

            frame.accumulativeScore =
                when {
                    frame.spare() -> {

                        previous +
                        frame.frameScore +
                            if (index + 1 < scoreSheet.frames.size)
                                scoreSheet.frames[index + 1].first!! //TODO remove !!
                            else 0
                    }

                    frame.strike() -> {
                        previous + frame.frameScore + getNextTwoScoresForStrike(index)
                    }

                    frame.notSpareNotStrike() -> {
                        previous + frame.frameScore
                    }

                    else -> -1//TODO throw exception
                }
        }
    }

    private fun getNextTwoScoresForStrike(currentIndex: Int): Int {

        return if (currentIndex + 1 < scoreSheet.frames.size) {

            val nextFrame = scoreSheet.frames[currentIndex + 1]

            if (nextFrame.strike()) {

                10 + if (currentIndex + 2 < scoreSheet.frames.size)
                        scoreSheet.frames[currentIndex + 2].first!! //TODO remove!!

                    else if (currentIndex == 10-1-1)//penultimo
                        scoreSheet.frames[currentIndex + 1].second!! //TODO remove!!
                    else 0

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
            val currentThird = frame.third ?: 0

            frame.frameScore =
                when {
                    frame.notSpareNotStrike() -> currentFirst + currentSecond
                    frame.spare() -> BowlingConstants.ALL_PINS_DOWN_VALUE

                    frame.strike() -> {
                        if (frame.position < 10) BowlingConstants.ALL_PINS_DOWN_VALUE
                        else currentFirst + currentSecond + currentThird
                    }

                    else -> -1//TODO throw exception
                }

        }
    }


}