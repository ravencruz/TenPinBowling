package core

import constants.BowlingConstants

data class BowlingFrame(
    val position: Int,

    var first: Int? = null,
    var second: Int? = null,
    var third: Int? = null,

    var frameScore: Int = 0,
    var accumulativeScore: Int = 0,
) {

    fun complete(): Boolean {

        return if (position == 10) { //TODO last position

            if (first == 10) //TODO its a STRIKE. its a better way to express business logic ?
                second != null && third != null
            else first != null && second != null

        } else {
            if (first != null && second != null) {
                true
            } else {
                first == 10
            }
        }
    }

    //TODO remove !!
    fun spare() = first != null && second != null && (first!! + second!!) == BowlingConstants.ALL_PINS_DOWN_VALUE

//    fun strike() = second == null && first == BowlingConstants.ALL_PINS_DOWN_VALUE
    fun strike() = first == BowlingConstants.ALL_PINS_DOWN_VALUE

    fun notSpareNotStrike() =
        first != null && second != null && (first!! + second!!) < BowlingConstants.ALL_PINS_DOWN_VALUE


    fun getPinFalls(): String {
        return when {
            strike() -> {
                val s = if (second != null) second else ""
                val t = if (third != null) third else ""
                "\tX\t $s \t $t"
            }
            spare() -> "$first \t/\t"
            else -> "$first \t $second\t"
        }
    }

    fun getScoreAsString(): String {
        return "\t$accumulativeScore\t"
    }

}
