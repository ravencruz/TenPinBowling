import constants.GameConstants
import game.ScoreCalculator
import game.ScoreSheet
import reader.BowlingFileReader
import reader.BowlingFrameParser

/**
 *
 */
class Main {

    fun execute(file: String) {
        val parser = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingBufferedReader(file)
        val scores: List<ScoreSheet> = parser.readFrames(lines)

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

fun main(args: Array<String>) {
    println("Welcome to Bowling Program.")
    println("Arguments: ${args.joinToString()}")

    val main = Main()
    val fileParam = if(args.isNotEmpty()) args[0] else "/home/scruz/repos/personal/challenges/TenPinBowling/src/main/resources/sample.txt"
    main.execute(fileParam)
}

