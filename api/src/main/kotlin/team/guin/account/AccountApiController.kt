package team.guin.account

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.account.dto.AccountDetail
import team.guin.account.dto.AccountUpdateRequest
import team.guin.common.CommonResponse
import team.guin.config.annotation.AccountSession
import team.guin.login.dto.AccountProfile

@RestController
@RequestMapping("/account")
class AccountApiController(
    private val accountApiService: AccountApiService,
) {

    @PutMapping
    fun update(@AccountSession accountProfile: AccountProfile, @RequestBody accountUpdateRequest: AccountUpdateRequest): CommonResponse<AccountDetail> {
        val account = accountApiService.updateInfo(accountProfile.id, accountUpdateRequest.nickname, accountUpdateRequest.imageId)
        return CommonResponse.okWithDetail(AccountDetail(account.email, account.nickname, account.imageUrl))
    }

    @DeleteMapping
    fun delete(@AccountSession accountProfile: AccountProfile): CommonResponse<Unit> {
        accountApiService.delete(accountProfile.id)
        return CommonResponse.ok()
    }
}
