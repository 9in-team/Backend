package team.guin.api.service

import org.springframework.stereotype.Service
import team.guin.api.controller.request.JoinRequest
import team.guin.api.repository.AccountRepository
import team.guin.domain.Account

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {
    fun join(joinRequest: JoinRequest): Account {
        val account = Account.create(joinRequest.email, joinRequest.nickname, joinRequest.imageId)
        return accountRepository.save(account)
    }
}
