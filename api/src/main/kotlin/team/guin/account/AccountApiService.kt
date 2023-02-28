package team.guin.account

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.domain.account.Account

@Service
@Transactional(readOnly = true)
class AccountApiService(
    private val accountApiRepository: AccountApiRepository,
) {
    @Transactional
    fun join(email: String, nickname: String, imageId: String): Account {
        val account = Account.create(email, nickname, imageId)
        return accountApiRepository.save(account)
    }

    @Transactional
    fun updateInfo(id: Long, nickname: String, imageId: String): Account {
        val account = this.findByAccountId(id)
        account.updateInfo(nickname, imageId)
        return account
    }

    private fun findByAccountId(id: Long): Account {
        return accountApiRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }
}
