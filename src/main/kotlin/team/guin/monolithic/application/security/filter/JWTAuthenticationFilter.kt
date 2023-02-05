package team.guin.monolithic.application.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.guin.monolithic.application.security.config.SecurityProperties
import team.guin.monolithic.application.security.service.TokenProvider
import team.guin.monolithic.domain.user.User
import java.io.IOException

class JWTAuthenticationFilter(
  private val authManager: AuthenticationManager,
  private val securityProperties: SecurityProperties,
  private val tokenProvider: TokenProvider
) : UsernamePasswordAuthenticationFilter() {

  @Throws(AuthenticationException::class)
  override fun attemptAuthentication(
    req: HttpServletRequest,
    res: HttpServletResponse?
  ): Authentication {
    return try {
      val mapper = jacksonObjectMapper()

      val creds = mapper
        .readValue<User>(req.inputStream)

      /*TODO - 이곳에서 카카오랑 통신해서 username 갖고오기*/
      authManager.authenticate(
        UsernamePasswordAuthenticationToken(
          creds.userNickname,
          null,
          null
        )
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
    authentication: Authentication
  ) {
    val token = tokenProvider.createToken(authentication)
    res.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
  }
}
