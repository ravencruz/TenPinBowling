package reader

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class BowlingFileReaderTest {

    @Test
    fun `read file`() {
        val res = BowlingFileReader.readFileAsLinesUsingUseLines("/kotlin.txt")
        Assertions.assertEquals(5, res.size)
    }

}