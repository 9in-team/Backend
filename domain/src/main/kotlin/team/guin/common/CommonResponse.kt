package team.guin.common

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonResponse<T> private constructor(
    val detail: T? = null,
    val errorMessage: String? = null,
) {
    companion object {
        fun ok() = CommonResponse<Unit>()
        fun <T> okWithDetail(detail: T) = CommonResponse(detail)
        fun error(errorMessage: String) = CommonResponse<Unit>(errorMessage = errorMessage)
    }
}
