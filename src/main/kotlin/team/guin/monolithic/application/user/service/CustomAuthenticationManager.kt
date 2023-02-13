package team.guin.monolithic.application.user.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationManager(
    private val userSerivce: CustomUserDetailsService,
) : AuthenticationManager {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val user = userSerivce.loadUserByUsername(authentication.name)
        return UsernamePasswordAuthenticationToken(user.username, "", user.authorities)
    }
}