import constants.GameConstants
import game.ScoreCalculator
import game.ScoreSheet
import reader.BowlingFileReader
import reader.BowlingFrameParser


class Main {

    fun execute(file: String) {
        val lines = BowlingFileReader.readFileAsLinesUsingBufferedReader(file)
        val parser = BowlingFrameParser()
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

    if (args.isNotEmpty()) {
        val main = Main()
        main.execute(args[0])
    } else {
        println("The full path of the file is required i.e: \n java -jar target/consoleApp-1.0.0-jar-with-dependencies.jar /sample.txt")
    }

}

