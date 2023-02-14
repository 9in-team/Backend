package team.guin.monolithic.application.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.guin.monolithic.infrastructure.kakao.service.KakaoApiService
import team.guin.monolithic.application.security.config.SecurityProperties
import team.guin.monolithic.application.security.service.TokenProvider
import team.guin.monolithic.application.user.dto.ReqLoginDTO
import team.guin.monolithic.infrastructure.kakao.dto.KakaoDetail
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val securityProperties: SecurityProperties,
    private val tokenProvider: TokenProvider,
    private val kakaoApiService: KakaoApiService,
) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?,
    ): Authentication {
        return try {
            val mapper = jacksonObjectMapper()

            val creds = mapper.readValue<ReqLoginDTO>(req.inputStream)

            val jsonData = kakaoApiService.getUserInfoFromKakaoAccessToken(creds.kakaoAccessToken)
            val kakaoDetail = KakaoDetail.from(jsonData)
            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    kakaoDetail.kakao_account["email"],
                    "",
                    ArrayList(),
                ),
            )
        } catch (e: IOException) {
            throw AuthenticationServiceException(e.message)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain?,
        authentication: Authentication,
    ) {
        val token = tokenProvider.createToken(authentication)
        res.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
    }
}