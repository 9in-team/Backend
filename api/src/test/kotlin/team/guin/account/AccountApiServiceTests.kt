package team.guin.account

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.guin.domain.account.Account

@SpringBootTest
class AccountApiServiceTests(
    private val accountApiService: AccountApiService,
    private val accountApiRepository: AccountApiRepository,
) : FreeSpec({
    "updateInfo" - {
        "회원이 정보를 변경하면 정보를 업데이트 한다." - {
            // given
            val email = "a1@a.com"
            val nickname = "nickname1"
            val imageId = "https://imugr.com/example"
            val savedAccount = accountApiRepository.save(Account.create(email, nickname, imageId))
            val changeNickname = "changeNickname"
            val changeImageId = "https://imugr.com/change"

            // when
            val updatedAccount =
                accountApiService.updateInfo(savedAccount.id, changeNickname, changeImageId)

            // then
            val findUpdateAccount = accountApiRepository.findById(updatedAccount.id).get()
            findUpdateAccount.email shouldBe email
            findUpdateAccount.imageUrl shouldBe changeImageId
            findUpdateAccount.nickname shouldBe changeNickname
        }

        "해당하는 Id 존재하지 않을경우 예외가 발생한다." - {
            // given
            val id: Long = -1
            val nickname = "nickname"
            val imageId = "https://imugr.com/example"

            // when
            val exception =
                shouldThrow<IllegalArgumentException> {
                    accountApiService.updateInfo(
                        id,
                        nickname,
                        imageId,
                    )
                }

            // then
            exception.message shouldBe "유저가 존재하지 않습니다."
        }
    }

    "delete" - {
        "탈퇴한 유저는 조회되지 않는다" - {
            // given
            val savedAccount3 =
                accountApiRepository.save(Account.create("t3@t.com", "test3", "https://htt3.co.kr"))

            // when
            accountApiService.delete(savedAccount3.id)

            // then
            val result = accountApiRepository.findByIdOrNull(savedAccount3.id)
            result shouldBe null
        }
        "해당하는 Id 존재하지 않을경우 예외가 발생한다." - {
            // given
            val id: Long = -1

            // when
            val exception =
                shouldThrow<IllegalArgumentException> {
                    accountApiService.delete(
                        id,
                    )
                }

            // then
            exception.message shouldBe "유저가 존재하지 않습니다."
        }
    }
}) {
    override suspend fun beforeAny(testCase: TestCase) {
        withContext(Dispatchers.IO) {
            accountApiRepository.deleteAll()
        }
    }
}
