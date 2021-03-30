import constants.GameConstants
import game.ScoreCalculator
import game.ScoreSheet
import reader.BowlingFrameFileReader

/**
 *
 */
class Main {

    fun execute() {
        val reader = BowlingFrameFileReader()
        val scores: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/sample.txt").file)

        println(getHeader())
        scores.forEach {
            val calculator = ScoreCalculator(it)
            calculator.fillScores()
            println(it)
        }
    }

    private fun getHeader(): String {
        var header = "Frame \t\t"
        for (frameX in 1..GameConstants.FRAME_LIMIT) {
            header += "\t $frameX \t"
        }
        return header
    }

}

fun main() {
    println("Welcome to Bowling Program the input file should be at resources folder with the sample.txt name")
    val main = Main()
    main.execute()
}

