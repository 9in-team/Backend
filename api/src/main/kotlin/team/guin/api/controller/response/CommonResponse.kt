package team.guin.api.controller.response

import team.guin.api.controller.response.enum.ResponseResult

data class CommonResponse<T>(
    val result: ResponseResult,
    val detail: T? = null,
    val description: String = "",
)
