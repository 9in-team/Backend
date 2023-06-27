package team.guin.webcommon.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionCode(
    val status: HttpStatus,
    val message: String,
) {
    DUPLICATE_ACCOUNT_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
}
