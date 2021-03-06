package core

import constants.ErrorMessage
import constants.GameConstants
import exceptions.BowlingException

data class BowlingFrame(
    val position: Int,

    var first: String? = null,
    var second: String? = null,
    var third: String? = null,

    var frameScore: Int = 0,
    var accumulativeScore: Int = 0,
) {

    fun complete(): Boolean {

        return if (position == GameConstants.FRAME_LIMIT) {

            if (first == GameConstants.ALL_PINS_DOWN_STRING_VALUE)
                second != null && third != null
            else first != null && second != null

        } else {
            // si no es la ultima posicion
            if (first != null && second != null) {
                true
            } else {
                first == GameConstants.ALL_PINS_DOWN_STRING_VALUE
            }
        }

    }

    fun spare() = first != null
            && second != null
            && (getFirstValueOrZero() + getSecondValueOrZero()) == GameConstants.ALL_PINS_DOWN_VALUE

    fun strike() = first == GameConstants.ALL_PINS_DOWN_STRING_VALUE

    fun notSpareNotStrike() = first != null
            && second != null
            && (getFirstValueOrZero() + getSecondValueOrZero()) < GameConstants.ALL_PINS_DOWN_VALUE


    fun getPinFallsAsString(): String {
        return when {
            strike() -> {
                val strikeSecond = if (second != null) "\t $second \t" else ""
                val strikeThird = if (third != null) third else ""
                "\t${GameConstants.FRAME_RESULT_STRIKE} $strikeSecond $strikeThird"
            }
            spare() -> "$first \t${GameConstants.FRAME_RESULT_SPARE}\t"
            else -> "$first \t $second\t"
        }
    }

    fun getScoreAsString(): String {
        return "\t$accumulativeScore\t"
    }

    fun getFirstValueOrZero(): Int {
        return evaluateAttributeValue(first)
    }

    fun getSecondValueOrZero(): Int {
        return evaluateAttributeValue(second)
    }

    fun getThirdValueOrZero(): Int {
        return evaluateAttributeValue(third)
    }

    private fun evaluateAttributeValue(value: String?): Int {
        val ballRoll = (if (value == GameConstants.FRAME_SCORE_FOUL) 0 else value?.toIntOrNull() ?: 0)
        if (invalidScoreValue(ballRoll)) throw BowlingException(String.format(ErrorMessage.INVALID_SCORE_VALUE, value))
        return ballRoll
    }

    private fun invalidScoreValue(scoreValue: Int) = scoreValue > GameConstants.FRAME_LIMIT || scoreValue < 0
}
