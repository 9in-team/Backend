package team.guin.webcommon.exception.response

import team.guin.webcommon.exception.CommonExceptionCode
import java.util.Date

data class ExceptionResponse(val message: String, val timestamp: Date) {
    constructor(exceptionCode: CommonExceptionCode) : this(exceptionCode.message, Date())
    constructor(message: String) : this(message, Date())
}
