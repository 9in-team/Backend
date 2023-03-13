package team.guin.security.kakao

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class KakaoAuthenticationToken(
    private val accountProfile: AccountProfile,
) : Authentication {
    private val authorities = mutableListOf<GrantedAuthority>()
    private var authenticated = false

    override fun getName(): String = accountProfile.email

    override fun getAuthorities() = authorities

    override fun getCredentials(): Any = Unit

    override fun getDetails() = accountProfile

    override fun getPrincipal(): String = accountProfile.email
    override fun isAuthenticated() = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }
}
