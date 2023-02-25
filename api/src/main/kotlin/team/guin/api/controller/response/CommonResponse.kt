package team.guin.api.controller.response

data class CommonResponse<T> private constructor(
    val detail: T? = null,
    val errorMessage: String? = null,
) {
    companion object {
        fun ok() = CommonResponse<Unit>()
        fun<T> okWithDetail(detail: T) = CommonResponse(detail)
        fun error(errorMessage: String) = CommonResponse<Unit>(errorMessage = errorMessage)
    }
}
