package team.guin.monolithic.application.user.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import team.guin.monolithic.application.user.repository.UserRepository

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userEmail: String): UserDetails {
        val user = userRepository.findByEmail(userEmail)
            .orElseThrow { UsernameNotFoundException("The username $userEmail doesn't exist") }

        var authorities: MutableList<GrantedAuthority> = mutableListOf()
        val roleList = user.getRoleList()
        roleList.forEach { r -> authorities.add(SimpleGrantedAuthority(r.toString())) }
        return User(user.email, "", authorities)
    }
}