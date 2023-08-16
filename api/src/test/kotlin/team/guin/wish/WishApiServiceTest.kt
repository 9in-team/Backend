package team.guin.wish

import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.team.Team
import team.guin.domain.wish.Wish
import team.guin.team.TeamApiRepository
import java.util.*

class WishApiServiceTest : FreeSpec({
    lateinit var wishApiService: WishApiService
    lateinit var wishApiRepository: WishApiRepository
    lateinit var accountApiRepository: AccountApiRepository
    lateinit var teamApiRepository: TeamApiRepository

    beforeTest {
        wishApiRepository = mockk<WishApiRepository>()
        accountApiRepository = mockk<AccountApiRepository>()
        teamApiRepository = mockk<TeamApiRepository>()
        wishApiService = WishApiService(wishApiRepository, accountApiRepository, teamApiRepository)
    }

    "WishApiService" - {
        "Add" - {
            "찜하기를 생성한다." {
                // given
                val accountId: Long = 1
                val teamId: Long = 1
                val wishCreateRequest = WishCreateRequest(teamId)

                val mockAccount = mockk<Account>()
                val mockTeam = mockk<Team>()

                every { accountApiRepository.findById(accountId) } returns Optional.of(mockAccount)
                every { teamApiRepository.findById(teamId) } returns Optional.of(mockTeam)
                // when

                wishApiService.create(accountId, wishCreateRequest)
                // then
            }
        }
    }
})

data class WishCreateRequest(
    val teamId: Long,
)

class WishApiService(
    private val wishApiRepository: WishApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamApiRepository: TeamApiRepository,
) {
    fun create(accountId: Long, wishCreateRequest: WishCreateRequest): Wish {
        val accountOptional = accountApiRepository.findById(accountId)
        val teamOptional = teamApiRepository.findById(wishCreateRequest.teamId)

        if (!accountOptional.isPresent || !teamOptional.isPresent) {
            throw IllegalStateException("Either account or team not found.")
        }

        val account = accountOptional.get()
        val team = teamOptional.get()

        val wish = Wish.create(account, team)
        return wishApiRepository.save(wish)
    }
}
