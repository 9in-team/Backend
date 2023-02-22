package team.guin.api.service

import org.springframework.stereotype.Service
import team.guin.api.controller.request.JoinRequest
import team.guin.api.repository.AccountApiRepository
import team.guin.domain.Account

@Service
class AccountApiService(
    private val accountApiRepository: AccountApiRepository,
) {
    fun join(joinRequest: JoinRequest): Account {
        val account = Account.create(joinRequest.email, joinRequest.nickname, joinRequest.imageId)
        return accountApiRepository.save(account)
    }
}
