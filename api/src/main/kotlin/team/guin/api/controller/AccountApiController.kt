package team.guin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.guin.api.controller.request.JoinRequest
import team.guin.api.controller.response.CommonResponse
import team.guin.api.controller.response.data.AccountData
import team.guin.api.controller.response.enum.ResponseResult
import team.guin.api.service.AccountApiService

@RestController
class AccountApiController(
    private val accountApiService: AccountApiService,
) {
    @PostMapping("/account")
    fun join(@RequestBody joinRequest: JoinRequest): CommonResponse<AccountData> {
        val account = accountApiService.join(joinRequest.email, joinRequest.nickname, joinRequest.imageId)
        return CommonResponse(result = ResponseResult.SUCCESS, data = AccountData(account.email, account.nickname, account.imageId))
    }
}
