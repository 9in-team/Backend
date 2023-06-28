package team.guin.login.apple

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.login.apple.util.AppleLoginUtil
import team.guin.login.dto.AccountProfile
import java.util.*

@Service
class AppleLoginApiService(
    private val accountApiRepository: AccountApiRepository
) {
    fun joinOrLogin(idToken: String): AccountProfile {
        val email = AppleLoginUtil.getPayload(idToken).email
        if (email.isEmpty()) throw BadCredentialsException("애플 로그인 실패 (이메일이 비어있음)")

        val account = accountApiRepository.findByEmail(email)
            ?: join(email)

        return AccountProfile.from(account)
    }

    private fun join(email: String): Account {
        var nickname = email.substring(0, 10)

        while (accountApiRepository.findByNickname(nickname) != null) {
            nickname = getRandomNickname()
        }

        return accountApiRepository.save(
            Account.create(email, nickname)
        )
    }

    private fun getRandomNickname(): String {
        return UUID.randomUUID().toString().split("-")[0]
    }
}
