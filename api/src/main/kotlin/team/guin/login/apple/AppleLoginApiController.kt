package team.guin.login.apple

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.login.apple.dto.AppleLoginRequest
import team.guin.login.dto.AccountProfile
import javax.servlet.http.HttpSession

@RestController
class AppleLoginApiController(
    private val appleLoginApiService: AppleLoginApiService,
) {
    @PostMapping(value = ["/loginWithApple"])
    fun joinOrLogin(
        @RequestBody appleLoginRequest: AppleLoginRequest,
        httpSession: HttpSession,
    ): CommonResponse<AccountProfile> {
        val accountProfile = appleLoginApiService.joinOrLogin(appleLoginRequest.id_token)
        httpSession.setAttribute("USER", accountProfile)

        return CommonResponse.okWithDetail(accountProfile)
    }
}
