package team.guin.security.kakao

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account

@Component
class KakaoAuthenticationManager(
    private val accountApiRepository: AccountApiRepository
): AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        val kakaoUserInfo = authentication.details as KakaoUserInfo
        print(kakaoUserInfo.nickname)
        val account = accountApiRepository.findByEmail(authentication.name)
            ?: accountApiRepository.save(Account.create(kakaoUserInfo.email, kakaoUserInfo.nickname, kakaoUserInfo.imageUrl))

        (authentication.authorities as MutableList<GrantedAuthority>).add(SimpleGrantedAuthority(account.accountRole.toString()))
        return authentication
    }
}
