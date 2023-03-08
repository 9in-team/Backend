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
        val kakaoProfile = authentication.details as KakaoProfile
        print(kakaoProfile.nickname)
        val account = accountApiRepository.findByEmail(authentication.name)
            ?: accountApiRepository.save(Account.create(kakaoProfile.email, kakaoProfile.nickname, kakaoProfile.imageUrl))

        val kakaoAuthenticationToken = KakaoAuthenticationToken(KakaoProfile(account.email, account.nickname, account.imageId))
        kakaoAuthenticationToken.authorities.add(SimpleGrantedAuthority(account.accountRole.toString()))

        return kakaoAuthenticationToken
    }
}
