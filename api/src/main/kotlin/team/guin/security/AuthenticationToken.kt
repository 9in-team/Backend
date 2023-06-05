package team.guin.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.SpringSecurityCoreVersion
import team.guin.domain.account.enumeration.AccountRole

class AuthenticationToken(
    private val name: String,
) : Authentication {
    private var authorities = emptyList<GrantedAuthority>()
    private var authenticated: Boolean = true

    @JsonIgnore override fun getCredentials(): Any? = null

    @JsonIgnore override fun getDetails(): Any? = null

    @JsonIgnore override fun getPrincipal(): Any = name

    @JsonIgnore override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(authenticated: Boolean) {
        this.authenticated = authenticated
    }

    override fun getName(): String = name
    override fun getAuthorities() = authorities.toList()
}

data class CustomGrantedAuthority(val role: String = "") : GrantedAuthority {
    private val authority = role

    companion object {
        private const val serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID
    }

    override fun getAuthority(): String {
        return role
    }
}
