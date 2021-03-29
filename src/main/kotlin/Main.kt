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

        println(scores.size)

        val jeffScoreSheet: ScoreSheet = scores[0]
        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()




        println(getHeader())
        println(jeffScoreSheet)


        val jhonScoreSheet: ScoreSheet = scores[1]
        val calculator2 = ScoreCalculator(jhonScoreSheet)
        calculator2.fillScores()

        println(jhonScoreSheet)
    }

    private fun getHeader(): String {
        var header = "Frame \t\t"
        for (frameX in 1..10) {
            header += "\t $frameX \t"
        }
        return header
    }

}

fun main(args: Array<String>) {
    println("Hello World!")
    val main = Main()
    main.execute()
}

