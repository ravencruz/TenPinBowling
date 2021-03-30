package exceptions

import java.lang.Exception

class BowlingException(
    override val message: String,
): Exception(message)