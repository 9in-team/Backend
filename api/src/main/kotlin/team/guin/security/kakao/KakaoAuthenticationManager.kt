package team.guin.security.kakao

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account

@Component
class KakaoAuthenticationManager(
    private val accountApiRepository: AccountApiRepository,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        val kakaoDetailProfile = authentication.details as KakaoDetailProfile
        val account = accountApiRepository.findByEmail(authentication.name)
            ?: accountApiRepository.save(Account.create(kakaoDetailProfile.email, kakaoDetailProfile.nickname, kakaoDetailProfile.imageUrl))

        val kakaoAuthenticationToken = KakaoAuthenticationToken(AccountProfile.from(account))
        kakaoAuthenticationToken.authorities.add(SimpleGrantedAuthority(account.accountRole.toString()))

        return kakaoAuthenticationToken
    }
}
