package team.guin.login.apple

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.login.apple.dto.AppleLoginRequest
import team.guin.login.dto.AccountProfile

@RestController
class AppleLoginApiController(
    private val appleLoginApiService: AppleLoginApiService
) {
    @PostMapping(value = ["/loginWithApple"])
    fun joinOrLogin(appleLoginRequest: AppleLoginRequest): CommonResponse<AccountProfile> {
        val accountProfile = appleLoginApiService.joinOrLogin(appleLoginRequest.id_token)

        return CommonResponse.okWithDetail(accountProfile)
    }

}
