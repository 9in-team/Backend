package team.guin.webcommon.exception.response

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: Map<String, String>?,
) {
    constructor(status: Int, error: String) : this(now(), status, error, emptyMap())
    constructor(status: Int, error: String, message: Map<String, String>?) : this(now(), status, error, message)
}
