package team.guin.account

import org.springframework.stereotype.Service
import team.guin.domain.account.Account

@Service
class AccountApiService(
    private val accountApiRepository: AccountApiRepository,
) {
    fun join(email: String, nickname: String, imageId: String): Account {
        val account = Account.create(email, nickname, imageId)
        return accountApiRepository.save(account)
    }
}
