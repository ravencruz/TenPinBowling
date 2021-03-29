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

        Assertions.assertEquals(9, jhonScoreSheet.getFrameScore(2))
    }

    @Test
    fun `spare frame value`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        val jhonScoreSheet: ScoreSheet = frameList[1]

        Assertions.assertEquals("Jeff", jeffScoreSheet.player)
        Assertions.assertEquals("John", jhonScoreSheet.player)

        val calculator = ScoreCalculator(jhonScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(BowlingConstants.ALL_PINS_DOWN_VALUE, jhonScoreSheet.getFrameScore(1))
    }

    @Test
    fun `strike frame value`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        Assertions.assertEquals("Jeff", jeffScoreSheet.player)

        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(BowlingConstants.ALL_PINS_DOWN_VALUE, jeffScoreSheet.getFrameScore(1))
    }


    @Test
    fun `on spares rule is you get 10 pins plus the next ball`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jhonScoreSheet: ScoreSheet = frameList[1]

        val calculator = ScoreCalculator(jhonScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(16, jhonScoreSheet.getAccumulativeScore(1))
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

        Assertions.assertEquals(20, jeffScoreSheet.getAccumulativeScore(1))
    }

    @Test
    fun `on non strike and non spare rule is prev plus current`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        Assertions.assertEquals("Jeff", jeffScoreSheet.player)

        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(39, jeffScoreSheet.getAccumulativeScore(2))
        Assertions.assertEquals(48, jeffScoreSheet.getAccumulativeScore(3))
        Assertions.assertEquals(66, jeffScoreSheet.getAccumulativeScore(4))
        Assertions.assertEquals(74, jeffScoreSheet.getAccumulativeScore(5))
        Assertions.assertEquals(84, jeffScoreSheet.getAccumulativeScore(6))
        Assertions.assertEquals(90, jeffScoreSheet.getAccumulativeScore(7))
    }

    @Test
    fun `on non strike and non spare rule is prev plus current JHON`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[1]
        Assertions.assertEquals("John", jeffScoreSheet.player)

        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(25, jeffScoreSheet.getAccumulativeScore(2))
        Assertions.assertEquals(44, jeffScoreSheet.getAccumulativeScore(3))
        Assertions.assertEquals(53, jeffScoreSheet.getAccumulativeScore(4))
        Assertions.assertEquals(82, jeffScoreSheet.getAccumulativeScore(5))
        Assertions.assertEquals(101, jeffScoreSheet.getAccumulativeScore(6))
        Assertions.assertEquals(110, jeffScoreSheet.getAccumulativeScore(7))
        Assertions.assertEquals(124, jeffScoreSheet.getAccumulativeScore(8))
        Assertions.assertEquals(132, jeffScoreSheet.getAccumulativeScore(9))
        Assertions.assertEquals(151, jeffScoreSheet.getAccumulativeScore(10))
    }

    @Test
    fun `on double strike ???`() {
        val reader = BowlingFrameFileReader()
        val frameList: List<ScoreSheet> = reader.readFramesFromFile(javaClass.getResource("/ten_shots.txt").file)
        Assertions.assertEquals(2, frameList.size)

        val jeffScoreSheet: ScoreSheet = frameList[0]
        Assertions.assertEquals("Jeff", jeffScoreSheet.player)

        val calculator = ScoreCalculator(jeffScoreSheet)
        calculator.fillScores()

        Assertions.assertEquals(120, jeffScoreSheet.getAccumulativeScore(8))

        Assertions.assertEquals(10, jeffScoreSheet.getFrameScore(9))
        Assertions.assertEquals(148, jeffScoreSheet.getAccumulativeScore(9))

        Assertions.assertEquals(19, jeffScoreSheet.getFrameScore(10))
        Assertions.assertEquals(167, jeffScoreSheet.getAccumulativeScore(10))
    }

}