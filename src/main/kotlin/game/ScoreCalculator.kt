package game

import constants.ErrorMessage
import constants.GameConstants
import exceptions.BowlingException

class ScoreCalculator(private val scoreSheet: ScoreSheet) {

    fun fillScores() {
        if (scoreSheet.isComplete()) {
            calculateIndividualFrameScore()
            calculateAccumulativeScore()
        } else throw BowlingException(ErrorMessage.INSUFFICIENT_DATA)
    }

    private fun calculateIndividualFrameScore() {
        scoreSheet.frames.forEach { frame ->

            val currentFirst = frame.getFirstValueOrZero()
            val currentSecond = frame.getSecondValueOrZero()
            val currentThird = frame.getThirdValueOrZero()

            frame.frameScore =
                when {
                    frame.strike() -> {
                        if (frame.position < GameConstants.FRAME_LIMIT) GameConstants.ALL_PINS_DOWN_VALUE
                        else currentFirst + currentSecond + currentThird
                    }
                    else -> currentFirst + currentSecond
                }
        }
    }

    private fun calculateAccumulativeScore() {
        scoreSheet.frames.forEachIndexed { index, frame ->

            val previous = if (index-1 >= 0) scoreSheet.frames[index-1].accumulativeScore else 0

            frame.accumulativeScore =
                when {
                    frame.spare() -> previous + frame.frameScore + getNextScoreForSpare(index)
                    frame.strike() -> previous + frame.frameScore + getNextTwoScoresForStrike(index)
                    frame.notSpareNotStrike() -> previous + frame.frameScore
                    else -> throw BowlingException(ErrorMessage.UNEXPECTED_ERROR)
                }
        }
    }

    private fun getNextScoreForSpare(index: Int): Int {
        return if (index + 1 < scoreSheet.frames.size)
            scoreSheet.frames[index + 1].getFirstValueOrZero()
        else 0
    }

    private fun getNextTwoScoresForStrike(currentIndex: Int): Int {

        return if (currentIndex + 1 < scoreSheet.frames.size) {

            val nextFrame = scoreSheet.frames[currentIndex + 1]
            if (nextFrame.strike()) {

                GameConstants.ALL_PINS_DOWN_VALUE +
                    if (currentIndex + 2 < scoreSheet.frames.size)
                        scoreSheet.frames[currentIndex + 2].getFirstValueOrZero()

                    else if (currentIndex == 10-1-1)//penultimo
                        scoreSheet.frames[currentIndex + 1].getSecondValueOrZero()
                    else 0

            } else {
                nextFrame.frameScore
            }

        } else {
            0
        }
    }

}