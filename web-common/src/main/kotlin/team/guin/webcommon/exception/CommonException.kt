package team.guin.webcommon.exception

import java.lang.RuntimeException

class CommonException(val exceptionCode: CommonExceptionCode) : RuntimeException()
