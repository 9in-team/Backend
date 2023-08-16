package team.guin.wish

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.team.Team
import team.guin.domain.wish.Wish
import team.guin.team.TeamApiRepository
import java.util.*
import java.util.function.Function

class WishApiServiceTest : FreeSpec({
    lateinit var wishApiService: WishApiService
    lateinit var wishApiRepository: WishApiRepository
    lateinit var accountApiRepository: AccountApiRepository
    lateinit var teamApiRepository: TeamApiRepository

    beforeTest {
        accountApiRepository = mockk()
        teamApiRepository = mockk()
        wishApiRepository = WishMockApiRepository()
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
                every { mockTeam.id } returns teamId
                every { mockAccount.id } returns accountId
                // when
                val create = wishApiService.create(accountId, wishCreateRequest)
                // then
                create shouldBe instanceOf<Wish>()
                create.account.id shouldBe accountId
                create.team.id shouldBe teamId
            }
        }
        "account가 존재하지 않으면 IllegalArgumentException 예외를 던진다." {
            // given
            val accountId: Long = 1
            val teamId: Long = 1
            val wishCreateRequest = WishCreateRequest(teamId)

            every { accountApiRepository.findById(accountId) } returns Optional.empty()
            // when
            val exception = shouldThrow<IllegalArgumentException> {
                wishApiService.create(accountId, wishCreateRequest)
            }
            // then
            exception.message shouldBe "Account not found for ID: $accountId"
        }
        "Team이 존재하지 않으면 IllegalArgumentException 예외를 던진다." {
            // given
            val accountId: Long = 1
            val teamId: Long = 1
            val wishCreateRequest = WishCreateRequest(teamId)

            val mockAccount = mockk<Account>()

            every { accountApiRepository.findById(accountId) } returns Optional.of(mockAccount)
            every { teamApiRepository.findById(teamId) } returns Optional.empty()
            // when
            val exception = shouldThrow<IllegalArgumentException> {
                wishApiService.create(accountId, wishCreateRequest)
            }
            // then
            exception.message shouldBe "Team not found for ID: $teamId"
        }
    }
})

data class WishCreateRequest(
    val teamId: Long,
)

class WishMockApiRepository : WishApiRepository {
    override fun <S : Wish?> save(entity: S): S {
        return entity!!
    }

    override fun <S : Wish?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Wish> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<Wish> {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<Wish> {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableList<Wish> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Wish) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Wish>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>,
    ): R {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> saveAndFlush(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : Wish?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<Wish>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Long): Wish {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Wish {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Long): Wish {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Optional<Wish> {
        TODO("Not yet implemented")
    }
}
