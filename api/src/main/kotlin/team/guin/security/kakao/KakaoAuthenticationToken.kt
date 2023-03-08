package team.guin.security.kakao

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

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
