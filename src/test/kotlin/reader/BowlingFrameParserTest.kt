package reader

import exceptions.BowlingException
import game.ScoreSheet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


internal class BowlingFrameParserTest {


    @Test
    fun `read players from file`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/one_shot.txt")
        val frameList: List<ScoreSheet> = reader.readFrames(lines)
        Assertions.assertEquals(2, frameList.size)

        val scoreSheet1: ScoreSheet = frameList[0]
        val scoreSheet2: ScoreSheet = frameList[1]

        Assertions.assertEquals("Jeff", scoreSheet1.player)
        Assertions.assertEquals("John", scoreSheet2.player)
    }

    @Test
    fun `load players score with one shot`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/one_shot.txt")
        val frameList: List<ScoreSheet> = reader.readFrames(lines)
        Assertions.assertEquals(2, frameList.size)

        val scoreSheet1: ScoreSheet = frameList[0]
        val scoreSheet2: ScoreSheet = frameList[1]

        Assertions.assertEquals(10, scoreSheet1.frames[0].first)
        Assertions.assertEquals(3, scoreSheet2.frames[0].first)
    }

    @Test
    fun `load players score with more shots`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/five_shots.txt")
        val frameList: List<ScoreSheet> = reader.readFrames(lines)
        Assertions.assertEquals(2, frameList.size)

        val scoreSheet1: ScoreSheet = frameList[0]
        val scoreSheet2: ScoreSheet = frameList[1]

        //1
        Assertions.assertEquals(10, scoreSheet1.frames[0].first)
        Assertions.assertNull(scoreSheet1.frames[0].second)

        Assertions.assertEquals(3, scoreSheet2.frames[0].first)
        Assertions.assertEquals(7, scoreSheet2.frames[0].second)

        //2
        Assertions.assertEquals(7, scoreSheet1.frames[1].first)
        Assertions.assertEquals(3, scoreSheet1.frames[1].second)

        Assertions.assertEquals(6, scoreSheet2.frames[1].first)
        Assertions.assertEquals(3, scoreSheet2.frames[1].second)

        //3
        Assertions.assertEquals(9, scoreSheet1.frames[2].first)
        Assertions.assertEquals(0, scoreSheet1.frames[2].second)

        Assertions.assertEquals(10, scoreSheet2.frames[2].first)
        Assertions.assertNull(scoreSheet2.frames[2].second)

        //4
        Assertions.assertEquals(10, scoreSheet1.frames[3].first)
        Assertions.assertNull(scoreSheet1.frames[3].second)

        Assertions.assertEquals(8, scoreSheet2.frames[3].first)
        Assertions.assertEquals(1, scoreSheet2.frames[3].second)

        //5
        Assertions.assertEquals(0, scoreSheet1.frames[4].first)
        Assertions.assertEquals(8, scoreSheet1.frames[4].second)

        Assertions.assertEquals(10, scoreSheet2.frames[4].first)
        Assertions.assertNull(scoreSheet2.frames[4].second)
    }

    @Test
    fun `load players all score`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/ten_shots.txt")
        val frameList: List<ScoreSheet> = reader.readFrames(lines)
        Assertions.assertEquals(2, frameList.size)

        val scoreSheet1: ScoreSheet = frameList[0]
        val scoreSheet2: ScoreSheet = frameList[1]

        //6
        Assertions.assertEquals(8, scoreSheet1.frames[5].first)
        Assertions.assertEquals(2, scoreSheet1.frames[5].second)

        Assertions.assertEquals(10, scoreSheet2.frames[5].first)
        Assertions.assertNull(scoreSheet2.frames[5].second)

        //7
        Assertions.assertEquals(0, scoreSheet1.frames[6].first)
        Assertions.assertEquals(6, scoreSheet1.frames[6].second)

        Assertions.assertEquals(9, scoreSheet2.frames[6].first)
        Assertions.assertEquals(0, scoreSheet2.frames[6].second)

        //8
        Assertions.assertEquals(10, scoreSheet1.frames[7].first)
        Assertions.assertNull(scoreSheet1.frames[7].second)

        Assertions.assertEquals(7, scoreSheet2.frames[7].first)
        Assertions.assertEquals(3, scoreSheet2.frames[7].second)

        //9
        Assertions.assertEquals(10, scoreSheet1.frames[8].first)
        Assertions.assertNull(scoreSheet1.frames[8].second)

        Assertions.assertEquals(4, scoreSheet2.frames[8].first)
        Assertions.assertEquals(4, scoreSheet2.frames[8].second)

    }

    @Test
    fun `rules is in the 10th frame a strike gives you two more shots`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/ten_shots.txt")
        val frameList: List<ScoreSheet> = reader.readFrames(lines)
        Assertions.assertEquals(2, frameList.size)

        val scoreSheet1: ScoreSheet = frameList[0]
        val scoreSheet2: ScoreSheet = frameList[1]

        //10
        Assertions.assertEquals(10, scoreSheet1.frames[9].first)
        Assertions.assertEquals(8, scoreSheet1.frames[9].second)
        Assertions.assertEquals(1, scoreSheet1.frames[9].third)

        Assertions.assertEquals(10, scoreSheet2.frames[9].first)
        Assertions.assertEquals(9, scoreSheet2.frames[9].second)
        Assertions.assertEquals(0, scoreSheet2.frames[9].third)

        println(scoreSheet1)
        println(scoreSheet2)
    }

    @Test
    fun `out of range score value should throw exception`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/bad_input_incorrect_value.txt")
        //val resource = javaClass.getResource("/bad_input_incorrect_value.txt").file

        assertThrows<BowlingException> {
            reader.readFrames(lines)
        }
    }

    @Test
    fun `incorrect value in input should throw exception`() {
        val reader = BowlingFrameParser()
        val lines = BowlingFileReader.readFileAsLinesUsingUseLines("/bad_input_not_number.txt")
        //val resource = javaClass.getResource("/bad_input_not_number.txt").file

        assertThrows<BowlingException> {
            reader.readFrames(lines)
        }
    }

}