package team.guin.webcommon.exception.response

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val error: String,
    val message: Map<String, String>?,
) {
    constructor(error: String) : this(now(), error, emptyMap())
    constructor(error: String, message: Map<String, String>?) : this(now(), error, message)
}
