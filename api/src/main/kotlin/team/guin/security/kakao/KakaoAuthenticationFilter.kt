package team.guin.security.kakao

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.guin.common.CommonResponse
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class KakaoAuthenticationFilter(
    private val kakaoApiService: KakaoApiService,
    private val objectMapper: ObjectMapper,
    kakaoAuthenticationManager: KakaoAuthenticationManager,
) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = kakaoAuthenticationManager
    }

    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ): Authentication {
        val request = req.getLoginRequest()

        val accessToken = request.accessToken
            ?: throw AuthenticationServiceException("Invalid AccessToken")

        val kakaoDetailProfile = kakaoApiService.fetchKakaoDetailProfile(accessToken)
            ?: throw AuthenticationServiceException("Invalid KakaoUserInfo")

        val authenticationToken = KakaoAuthenticationToken(AccountProfile.from(kakaoDetailProfile))
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authentication: Authentication) {
        response.status = HttpStatus.OK.value()
        response.contentType = "application/json; charset=UTF-8"
        response.writer.write(objectMapper.writeValueAsString(CommonResponse.okWithDetail(authentication.details)))
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json; charset=UTF-8"
        response.writer.write(objectMapper.writeValueAsString(CommonResponse.error("accessToken이 유효하지 않습니다.")))
    }
}

fun HttpServletRequest.getLoginRequest(): LoginRequest {
    val mapper = ObjectMapper()
    val body = this.reader.readText()
    return mapper.readValue(body, LoginRequest::class.java)
}

data class LoginRequest(val accessToken: String?)
