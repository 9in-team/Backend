package team.guin.account

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.domain.account.Account
import team.guin.security.kakao.AccountProfile
import team.guin.security.kakao.KakaoApiService

@Service
class AccountApiService(
    private val accountApiRepository: AccountApiRepository,
    private val kakaoApiService: KakaoApiService,
) {
    fun joinOrLogin(accessToken: String): AccountProfile {
        val kakaoDetailProfile = kakaoApiService.fetchKakaoDetailProfile(accessToken)

        val account = accountApiRepository.findByEmail(kakaoDetailProfile.email)
            ?: accountApiRepository.save(kakaoDetailProfile.toEntity())

        return AccountProfile.from(account)
    }

    @Transactional
    fun updateInfo(id: Long, nickname: String, imageId: String): Account {
        val account = this.findById(id)
        account.updateInfo(nickname, imageId)
        return account
    }

    fun delete(id: Long) {
        val account = this.findById(id)
        accountApiRepository.deleteById(account.id)
    }

    private fun findById(id: Long): Account {
        return accountApiRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }
}
