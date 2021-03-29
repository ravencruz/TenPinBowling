package game

import constants.BowlingConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reader.BowlingFrameFileReader


internal class ScoreSheetTest {

    @Test
    fun `frame with less than 10 points should get score from frame itself`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jhonScoreSheet = frameList[1]
        val calculator = ScoreCalculator(jhonScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(9, jhonScoreSheet.getScore(2))
    }

    @Test
    fun `on spares rule is you get 10 pins plus the next ball`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        val jhonScoreSheet: ScoreSheet = frameList[1]

        Assertions.assertEquals("Jeff", jeffScoreSheet.player)
        Assertions.assertEquals("John", jhonScoreSheet.player)

        val calculator = ScoreCalculator(jhonScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(10, jhonScoreSheet.getScore(1))
    }

    @Test
    fun `on strike rule is you get 10 points plus the two next balls`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        Assertions.assertEquals("Jeff", jeffScoreSheet.player)

        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(BowlingConstants.ALL_PINS_DOWN_VALUE, jeffScoreSheet.getScore(1))
    }


}