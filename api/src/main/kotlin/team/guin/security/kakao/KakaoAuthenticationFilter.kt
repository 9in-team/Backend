package team.guin.security.kakao

import com.fasterxml.jackson.databind.ObjectMapper
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
        val json = getRequestBody(req)

        val accessToken = objectMapper.readTree(json).get("accessToken")?.asText()
            ?: throw AuthenticationServiceException("Invalid AccessToken")

        val kakaoUserInfo = kakaoApiService.fetchKakaoUserInfo(accessToken)
            ?: throw AuthenticationServiceException("Invalid KakaoUserInfo")

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
        response.writer.write(objectMapper.writeValueAsString(CommonResponse.okWithDetail(authentication.details)))
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json; charset=UTF-8"
        response.writer.write(objectMapper.writeValueAsString(CommonResponse.error("accessToken이 유효하지 않습니다.")))
    }
}

class KakaoAuthenticationToken(
    private val kakaoProfile: KakaoProfile,
) : Authentication {
    private val authorities = mutableListOf<GrantedAuthority>()
    private var authenticated = false

    override fun getName(): String = kakaoProfile.email

    override fun getAuthorities() = authorities

    override fun getCredentials(): Any = Unit

    override fun getDetails() = kakaoProfile

    override fun getPrincipal(): String = kakaoProfile.email
    override fun isAuthenticated() = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }
}
