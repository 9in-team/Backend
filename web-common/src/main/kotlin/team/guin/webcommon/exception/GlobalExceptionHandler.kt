package team.guin.webcommon.exception

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.guin.webcommon.exception.response.ErrorResponse

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
        val errorResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            error,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.allErrors.mapNotNull { error ->
            when (error) {
                is FieldError -> error.field to (error.defaultMessage ?: "유효하지 않은 필드입니다.")
                else -> error.objectName to (error.defaultMessage ?: "알 수 없는 오류가 발생하였습니다.")
            }
        }.toMap()

        val errorResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            errors,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(e: CommonException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            e.exceptionCode.status.value(),
            e.exceptionCode.message,
        )
        return ResponseEntity(errorResponse, e.exceptionCode.status)
    }
}
