package team.guin.wish

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.guin.account.AccountApiRepository
import team.guin.domain.team.enumeration.SubjectType
import team.guin.team.TeamApiRepository
import team.guin.util.createAccount
import team.guin.util.createTeam
import javax.transaction.Transactional

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
class WishApiServiceTest(
    private val wishApiService: WishApiService,
    private val accountApiRepository: AccountApiRepository,
    private val teamApiRepository: TeamApiRepository,
) : FreeSpec({
    "WishApiService" - {
        "Add" - {
            "찜하기를 생성한다." {
                // give
                val leader = accountApiRepository.createAccount()
                val projectTeam = teamApiRepository.createTeam(leader = leader, subjectType = SubjectType.PROJECT)
                val wish = Wish.create(projectTeam, leader)
                // when
                val add = wishApiService.add(wish)
                // then
                add.id shouldBeGreaterThan 0
                add.team.id shouldBe 1L
                add.account.id shouldBe 1L
                add.team shouldBeEqualToComparingFields projectTeam
                add.account shouldBeEqualToComparingFields leader
            }
        }
    }
})
