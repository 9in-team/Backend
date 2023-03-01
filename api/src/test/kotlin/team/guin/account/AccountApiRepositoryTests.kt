package team.guin.account

import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.guin.domain.account.Account
import team.guin.domain.account.enumeration.AccountRole

@SpringBootTest
class AccountApiRepositoryTests(
    private val accountApiRepository: AccountApiRepository,
) : FreeSpec({
    "findByEmail" - {
        "일치하는 이메일이 존재할 때 일치하는 Account를 반환한다" {
            // given
            val targetEmail = "email@a.com"
            val user = Account.create(targetEmail, "nickname", "imageId")
            withContext(Dispatchers.IO) {
                accountApiRepository.save(user)
            }

            // when
            val result =
                withContext(Dispatchers.IO) {
                    accountApiRepository.findByEmail(targetEmail)
                }

            // then
            val nickName = "nickname"
            val imageId = "imageId"
            result?.nickname shouldBe nickName
            result?.imageId shouldBe imageId
            result?.accountRole shouldBe AccountRole.USER
        }
        "이메일로 엔티티를 찾아올 수 없어 널값이 반환된다 " {
            // given
            val targetEmail = "test@naver.com"

            // when
            val account = withContext(Dispatchers.IO) {
                accountApiRepository.findByEmail(targetEmail)
            }

            // then
            account shouldBe null
        }
    }
    "findByIdOrNull" - {
        "해당하는 id가 없을 경우 널값을 반환한다." - {
            // given
            val id: Long = -1

            // when
            val result = withContext(Dispatchers.IO) {
                accountApiRepository.findByIdOrNull(id)
            }

            // then
            result shouldBe null
        }
        "해당하는 id가 있을경우 account 반환한다." - {
            // given
            val account = Account.create("a@a.com", "nickname", "imageId")
            accountApiRepository.save(account)

            // when
            val result =
                withContext(Dispatchers.IO) {
                    accountApiRepository.findByIdOrNull(account.id)
                }

            // then
            result?.nickname shouldBe account.nickname
            result?.imageId shouldBe account.imageId
            result?.email shouldBe account.email
        }
    }
}) {
    override suspend fun beforeAny(testCase: TestCase) {
        withContext(Dispatchers.IO) {
            accountApiRepository.deleteAll()
        }
    }
}
