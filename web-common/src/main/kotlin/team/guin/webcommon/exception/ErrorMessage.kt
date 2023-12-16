package team.guin.webcommon.exception

enum class ErrorMessage(val message: String) {
    INVALID_JSON_FORMAT("올바른 JSON 형식이 아닙니다."),
    INVALID_FIELD_TYPE("[{0}] 타입이 올바르지 않습니다."),
    MISSING_REQUIRED_FIELD("[{0}] 값은 필수입니다."),
    UNKNOWN_ERROR("JSON 파일 중 알 수 없는 오류가 발생하였습니다."),
    ACCESS_TOKEN_REQUIRED("accessToken은 필수값입니다."),
}
