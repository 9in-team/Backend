package team.guin.webcommon.exception

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import team.guin.webcommon.exception.response.ExceptionResponse

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [CommonException::class])
    fun handleCommonException(e: CommonException): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(e.exceptionCode)
        return ResponseEntity(exceptionResponse, e.exceptionCode.status)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ExceptionResponse> {
        val message: String = when (val cause = ex.cause) {
            is JsonParseException -> "올바른 JSON 형식이 아닙니다."
            is JsonMappingException -> {
                when (cause) {
                    is InvalidFormatException -> "[${cause.path.joinToString(", ") { it.fieldName }}] 타입이 올바르지 않습니다."
                    else -> "[${cause.path.joinToString(",") { it.fieldName }}] 값은 필수입니다."
                }
            }
            else -> "JSON 파 중 알 수 없는 오류가 발생하였습니다."
        }
        val exceptionResponse = ExceptionResponse(message)
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}
