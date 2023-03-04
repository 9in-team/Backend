package team.guin.security.kakao

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.guin.common.CommonResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class KakaoAuthenticationFilter(
    private val kakaoApiService: KakaoApiService,
    kakaoAuthenticationManager: KakaoAuthenticationManager,
) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = kakaoAuthenticationManager
    }

    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ): Authentication {
        val json = getRequestBody(req)

        val mapper = jacksonObjectMapper()
        val accessToken = mapper.readTree(json).get("accessToken")?.asText()
        accessToken ?: throw AuthenticationServiceException("Invalid AccessToken")

        val kakaoUserInfo = kakaoApiService.fetchKakaoUserInfo(accessToken)
        kakaoUserInfo ?: throw AuthenticationServiceException("Invalid KakaoUserInfo")
        println(kakaoUserInfo)

        val authenticationToken = KakaoAuthenticationToken(kakaoUserInfo)
        return authenticationManager.authenticate(authenticationToken)
    }

    private fun getRequestBody(request: HttpServletRequest): String {
        val inputStream = request.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }

        return stringBuilder.toString()
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authentication: Authentication) {
        response.status = HttpStatus.OK.value()
        response.contentType = "application/json; charset=UTF-8"
        response.writer.write(jacksonObjectMapper().writeValueAsString(authentication.details as KakaoUserInfo))
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json; charset=UTF-8"
        response.writer.write(jacksonObjectMapper().writeValueAsString(CommonResponse.error("accessToken이 유효하지 않습니다.")))
    }
}

class KakaoAuthenticationToken(
    private val kakaoUserInfo: KakaoUserInfo,
) : Authentication {
    private val authorities = mutableListOf<GrantedAuthority>()
    private var authenticated = false

    override fun getName(): String = kakaoUserInfo.email

    override fun getAuthorities() = authorities

    override fun getCredentials(): Any = Unit

    override fun getDetails() = kakaoUserInfo

    override fun getPrincipal(): String = kakaoUserInfo.email
    override fun isAuthenticated() = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }
}