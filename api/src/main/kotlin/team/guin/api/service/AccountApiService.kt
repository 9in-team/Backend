package team.guin.api.service

import org.springframework.stereotype.Service
import team.guin.api.repository.AccountApiRepository
import team.guin.domain.Account

@Service
class AccountApiService(
    private val accountApiRepository: AccountApiRepository,
) {
    fun join(email: String, nickname: String, imageId: String): Account {
        val account = Account.create(email, nickname, imageId)
        return accountApiRepository.save(account)
    }
}
