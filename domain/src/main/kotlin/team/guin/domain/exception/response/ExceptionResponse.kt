package team.guin.domain.exception.response

import team.guin.domain.exception.CommonExceptionCode
import java.util.Date

data class ExceptionResponse(val message: String, val timestamp: Date) {
    constructor(exceptionCode: CommonExceptionCode) : this(exceptionCode.message, Date())
}
