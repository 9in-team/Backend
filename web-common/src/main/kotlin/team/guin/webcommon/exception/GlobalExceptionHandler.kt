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
            is JsonParseException -> "올바른 JSON 형식이 아닙니다."
            is JsonMappingException -> {
                when (cause) {
                    is InvalidFormatException -> "[${cause.path.joinToString(", ") { it.fieldName }}] 타입이 올바르지 않습니다."
                    else -> "[${cause.path.joinToString(",") { it.fieldName }}] 값은 필수입니다."
                }
            }

            else -> "JSON 파일 중 알 수 없는 오류가 발생하였습니다."
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
