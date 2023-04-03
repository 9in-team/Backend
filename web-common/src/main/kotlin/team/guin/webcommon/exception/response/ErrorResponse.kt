package team.guin.webcommon.exception.response

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val error: String,
    val message: Map<String, String>,
) {
    companion object {
        fun createWithMessage(error: String, message: Map<String, String>): ErrorResponse {
            return ErrorResponse(now(), error, message)
        }

        fun create(error: String): ErrorResponse {
            return createWithMessage(error, emptyMap())
        }
    }
}
