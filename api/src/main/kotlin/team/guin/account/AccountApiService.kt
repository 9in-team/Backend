package team.guin.account

import org.springframework.data.repository.findByIdOrNull
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

    fun updateInfo(id: Long, email: String, nickname: String, imageId: String): Account {
        val account = this.findByAccountId(id)
        account.updateInfo(email, nickname, imageId)
        return account
    }

    private fun findByAccountId(id: Long): Account {
        return accountApiRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }
}
