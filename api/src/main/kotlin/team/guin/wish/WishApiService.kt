package team.guin.wish

import org.springframework.stereotype.Service
import team.guin.account.AccountApiRepository
import team.guin.domain.wish.Wish
import team.guin.team.TeamApiRepository

@Service
class WishApiService(
    private val wishApiRepository: WishApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamApiRepository: TeamApiRepository,
) {
    fun create(accountId: Long, wishCreateRequest: WishCreateRequest): Wish {
        val account = accountApiRepository.findById(accountId)
            .orElseThrow { IllegalArgumentException("Account not found for ID: $accountId") }
        val team = teamApiRepository.findById(wishCreateRequest.teamId)
            .orElseThrow { IllegalArgumentException("Team not found for ID: ${wishCreateRequest.teamId}") }

        val wish = Wish.create(account, team)
        return wishApiRepository.save(wish)
    }
}
