package reader

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


internal class BowlingFileReaderTest {

    @Test
    fun `read file`() {
        val res = BowlingFileReader.readFileAsLinesUsingUseLines("/kotlin.txt")
        Assertions.assertEquals(5, res.size)
    }

    @Test
    @Disabled
    fun `read file with full path`() {
        val res = BowlingFileReader.readFileAsLinesUsingBufferedReader("/home/scruz/repos/personal/challenges/kotlin.txt")
        Assertions.assertEquals(5, res.size)
    }

}