package team.guin.login.kakao

import org.springframework.stereotype.Service
import team.guin.account.AccountApiRepository
import team.guin.login.kakao.dto.AccountProfile

@Service
class KakaoLoginApiService(
    private val kakaoApiService: KakaoApiService,
    private val accountApiRepository: AccountApiRepository,
) {
    fun joinOrLogin(accessToken: String): AccountProfile {
        val kakaoDetailProfile = kakaoApiService.fetchKakaoDetailProfile(accessToken)

        val account = accountApiRepository.findByEmail(kakaoDetailProfile.email)
            ?: accountApiRepository.save(kakaoDetailProfile.toEntity())

        return AccountProfile.from(account)
    }
}
