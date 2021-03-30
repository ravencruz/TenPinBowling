package core

import constants.GameConstants

data class BowlingFrame(
    val position: Int,

    var first: Int? = null,
    var second: Int? = null,
    var third: Int? = null,

    var frameScore: Int = 0,
    var accumulativeScore: Int = 0,
) {

    fun complete(): Boolean {

        return if (position == GameConstants.FRAME_LIMIT) {

            if (first == GameConstants.ALL_PINS_DOWN_VALUE)
                second != null && third != null
            else first != null && second != null

        } else {
            // si no es la ultima posicion
            if (first != null && second != null) {
                true
            } else {
                first == GameConstants.ALL_PINS_DOWN_VALUE
            }
        }

    }

    //TODO remove !!
    fun spare() = first != null && second != null && (first!! + second!!) == GameConstants.ALL_PINS_DOWN_VALUE

    fun strike() = first == GameConstants.ALL_PINS_DOWN_VALUE

    fun notSpareNotStrike() =
        first != null && second != null && (first!! + second!!) < GameConstants.ALL_PINS_DOWN_VALUE


    fun getPinFalls(): String {
        return when {
            strike() -> {
                val s = if (second != null) second else ""
                val t = if (third != null) third else ""
                "\t${GameConstants.FRAME_RESULT_STRIKE}\t $s \t $t"
            }
            spare() -> "$first \t${GameConstants.FRAME_RESULT_SPARE}\t"
            else -> "$first \t $second\t"
        }
    }

    fun getScoreAsString(): String {
        return "\t$accumulativeScore\t"
    }

}
