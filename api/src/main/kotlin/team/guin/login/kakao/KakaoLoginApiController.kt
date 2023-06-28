package team.guin.login.kakao

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.login.dto.AccountProfile
import team.guin.login.kakao.dto.KakaoLoginRequest
import javax.servlet.http.HttpSession

@RestController
class KakaoLoginApiController(
    private val kakaoLoginApiService: KakaoLoginApiService,
) {
    @PostMapping("/loginWithKakao")
    fun loginWithKakao(
        @RequestBody kakaoLoginRequest: KakaoLoginRequest,
        httpSession: HttpSession,
    ): CommonResponse<AccountProfile> {
        val accessToken = kakaoLoginRequest.accessToken

        val accountProfile = kakaoLoginApiService.joinOrLogin(accessToken)
        httpSession.setAttribute("USER", accountProfile)

        return CommonResponse.okWithDetail(accountProfile)
    }
}
