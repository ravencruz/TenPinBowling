package core

data class Frame(
    val position: Int,
    var first: Int? = null,
    var second: Int? = null,
    var third: Int? = null,
) {

    fun getScore(): Int {
        val value1 = first ?: 0
        val value2 = second ?: 0

        return value1 + value2
    }

    fun complete(): Boolean {

        return  if (position == 10) {

                    if ( first == 10 ) //TODO its a STRIKE. its a better way to express business logic ?
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

}
