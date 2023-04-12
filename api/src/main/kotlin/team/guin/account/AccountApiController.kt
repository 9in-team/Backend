package team.guin.account

import org.springframework.web.bind.annotation.DeleteMapping
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
import team.guin.security.kakao.AccountProfile
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/account")
class AccountApiController(
    private val accountApiService: AccountApiService,
    private val kakaoApiService: KakaoApiService,
    private val accountApiRepository: AccountApiRepository,
) {
    @PostMapping
    fun join(
        @RequestBody accountJoinRequest: AccountJoinRequest,
        response: HttpServletResponse,
        httpSession: HttpSession,
    ): CommonResponse<AccountProfile> {
        val accessToken = accountJoinRequest.accessToken

        val accountProfile = accountApiService.joinOrLogin(accessToken)
        httpSession.setAttribute("USER", accountProfile)

        return CommonResponse.okWithDetail(accountProfile)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody accountUpdateRequest: AccountUpdateRequest): CommonResponse<AccountDetail> {
        val account = accountApiService.updateInfo(id, accountUpdateRequest.nickname, accountUpdateRequest.imageId)
        return CommonResponse.okWithDetail(AccountDetail(account.email, account.nickname, account.imageUrl))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): CommonResponse<Unit> {
        accountApiService.delete(id)
        return CommonResponse.ok()
    }
}
