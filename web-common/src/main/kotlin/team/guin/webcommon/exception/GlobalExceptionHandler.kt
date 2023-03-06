package team.guin.webcommon.exception

import org.springframework.http.ResponseEntity
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
}
