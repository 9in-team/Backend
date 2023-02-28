package team.guin.account

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.account.dto.AccountDetail
import team.guin.account.dto.AccountJoinRequest
import team.guin.account.dto.AccountUpdateRequest
import team.guin.common.CommonResponse

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

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody accountDetail: AccountUpdateRequest): CommonResponse<AccountDetail> {
        val account = accountApiService.updateInfo(id, accountDetail.email, accountDetail.nickname, accountDetail.imageId)
        return CommonResponse.okWithDetail(AccountDetail(account.email, account.nickname, account.imageId))
    }
}
