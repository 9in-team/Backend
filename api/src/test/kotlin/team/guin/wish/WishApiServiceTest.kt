package team.guin.wish

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import team.guin.account.AccountApiRepository
import team.guin.common.DatabaseCleaner
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.wish.Wish
import team.guin.team.TeamApiRepository
import team.guin.util.createAccount
import team.guin.util.createTeam

@Transactional
@SpringBootTest
class WishApiServiceTest(
    val wishApiService: WishApiService,
    val wishApiRepository: WishApiRepository,
    val teamApiRepository: TeamApiRepository,
    val accountApiRepository: AccountApiRepository,
    val databaseCleaner: DatabaseCleaner,
) : FreeSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Test))
    beforeEach {
        withContext(Dispatchers.IO) {
            databaseCleaner.cleanUp()
        }
    }
    "WishApiService" - {
        "찜하기를 생성한다." {
            // given
            val leader = accountApiRepository.createAccount()
            val createTeam = teamApiRepository.createTeam(leader = leader, subjectType = SubjectType.PROJECT)
            val wishCreateRequest = WishCreateRequest(createTeam.id)

            // when
            val create = wishApiService.create(leader.id, wishCreateRequest)

            // then
            create shouldBe instanceOf<Wish>()
            create.account shouldBeEqualToComparingFields leader
            create.team shouldBeEqualToComparingFields createTeam
        }
        "account가 존재하지 않으면 IllegalArgumentException 예외를 던진다." {
            // given
            val accountId: Long = 1
            val teamId: Long = 1
            val wishCreateRequest = WishCreateRequest(teamId)

            // when
            val exception = shouldThrow<IllegalArgumentException> {
                wishApiService.create(accountId, wishCreateRequest)
            }

            // then
            exception.message shouldBe "Account not found for ID: $accountId"
        }
        "Team이 존재하지 않으면 IllegalArgumentException 예외를 던진다." {
            // given
            val leader = accountApiRepository.createAccount()
            val teamId = 2L
            val wishCreateRequest = WishCreateRequest(teamId)

            // when
            val exception = shouldThrow<IllegalArgumentException> {
                wishApiService.create(leader.id, wishCreateRequest)
            }

            // then
            exception.message shouldBe "Team not found for ID: $teamId"
        }
        "찜하기를 취소한다." {
            // given
            val account = accountApiRepository.createAccount()
            val createTeam = teamApiRepository.createTeam(leader = account, subjectType = SubjectType.PROJECT)
            val wishCreateRequest = WishCreateRequest(createTeam.id)
            val create = wishApiService.create(account.id, wishCreateRequest)

            // when
            wishApiService.delete(account.id, create.id)

            // then
            val wish = wishApiRepository.findById(create.id)
            wish.isEmpty shouldBe true
        }
        "찜하기 삭제 시 찜하기가 존재하지 않으면 IllegalArgumentException 예외를 던진다." {
            // given
            val accountId: Long = 1
            val wishId: Long = 1

            // when, then
            val exception = shouldThrow<IllegalArgumentException> {
                wishApiService.delete(accountId, wishId)
            }
        }
        "찜하기 삭제 시, 본인것이 아니면 AccessDeniedException 예외가 발생한다." {
            // given
            val account = accountApiRepository.createAccount()
            val createTeam = teamApiRepository.createTeam(leader = account, subjectType = SubjectType.PROJECT)
            val wishCreateRequest = WishCreateRequest(createTeam.id)
            val create = wishApiService.create(account.id, wishCreateRequest)

            // when, then
            val exception = shouldThrow<AccessDeniedException> {
                wishApiService.delete(2, create.id)
            }
        }
    }
})
