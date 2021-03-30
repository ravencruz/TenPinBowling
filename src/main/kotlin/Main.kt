import constants.GameConstants
import game.ScoreCalculator
import game.ScoreSheet
import reader.BowlingFileReader
import reader.BowlingFrameParser

/**
 *
 */
class Main {

    fun execute(fileName: String = "sample.txt") {
        val parser = BowlingFrameParser()
//        val scores: List<ScoreSheet> = reader.readFramesFromFile(fileName)
//        val scores: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("./sample.txt").file)

//        val f = File(fileName)
//        println(f.absolutePath)

//        val scoreFile = this::class.java.getResource("/sample.txt").file
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/sample.txt")
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
    println("Argumentos: ${args.joinToString()}")
    val main = Main()
    main.execute()
}

