package team.guin.webcommon.exception

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.guin.webcommon.exception.response.ErrorResponse
import java.time.LocalDateTime.now

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val error: String = when (val cause = ex.cause) {
            is JsonParseException -> ErrorMessage.INVALID_JSON_FORMAT.message
            is JsonMappingException -> {
                when (cause) {
                    is InvalidFormatException -> ErrorMessage.INVALID_FIELD_TYPE.message.format(cause.path.joinToString(", ") { it.fieldName })
                    else -> ErrorMessage.MISSING_REQUIRED_FIELD.message.format(cause.path.joinToString(", ") { it.fieldName })
                }
            }

            else -> ErrorMessage.UNKNOWN_ERROR.message
        }
        val errorResponse = ErrorResponse.create(
            now(),
            error,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(e: CommonException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.create(
            now(),
            e.exceptionCode.message,
        )
        return ResponseEntity(errorResponse, e.exceptionCode.status)
    }
}
