package team.guin.webcommon.exception.response

import java.time.LocalDateTime

class ErrorResponse private constructor(
    val timeStamp: LocalDateTime,
    val error: String,
    val message: Map<String, String>,
) {
    companion object {
        fun createWithMessage(timeStamp: LocalDateTime, error: String, message: Map<String, String>): ErrorResponse {
            return ErrorResponse(timeStamp, error, message)
        }

        fun create(timeStamp: LocalDateTime, error: String): ErrorResponse {
            return createWithMessage(timeStamp, error, emptyMap())
        }
    }
}
