package team.guin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.api.controller.request.AccountJoinRequest
import team.guin.api.controller.response.CommonResponse
import team.guin.api.controller.response.detail.AccountDetail
import team.guin.api.service.AccountApiService

@RestController
@RequestMapping("/account")
class AccountApiController(
    private val accountApiService: AccountApiService,
) {
    @PostMapping
    fun join(@RequestBody accountJoinRequest: AccountJoinRequest): CommonResponse<AccountDetail> {
        val account = accountApiService.join(accountJoinRequest.email, accountJoinRequest.nickname, accountJoinRequest.imageId)
        val detail = AccountDetail(account.email, account.nickname, account.imageId)
        return CommonResponse.okWithDetail(detail)
    }
}
