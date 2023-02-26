package team.guin.domain.exception

import java.lang.RuntimeException

class CommonException(val exceptionCode: CommonExceptionCode) : RuntimeException()
