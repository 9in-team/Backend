package team.guin.account

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.guin.domain.account.Account
import team.guin.domain.account.enumeration.AccountRoles

@SpringBootTest
class AccountApiRepositoryTests(
    private val accountApiRepository: AccountApiRepository,
) : FreeSpec({
    "findByEmail" - {
        "이메일로 엔티티를 찾아올 수 있다" {
            // given
            val targetEmail = "email@a.com"
            val fromCode = AccountRoles.fromCode("USER")
            val user = Account.create(targetEmail, "nickname", "imageId", fromCode)
            withContext(Dispatchers.IO) {
                accountApiRepository.save(user)
            }

            // when
            val result =
                withContext(Dispatchers.IO) {
                    accountApiRepository.findByEmail(targetEmail)
                }

            // then
            result?.nickname shouldBe "nickname"
            result?.imageId shouldBe "imageId"
        }
    }
    "findByEmail Null" - {
        "이메일로 엔티티를 찾아올 수 없어 널값이 반환된다 " {
            // given
            val targetEmail = "test@naver.com"

            // when/then
            withContext(Dispatchers.IO) {
                accountApiRepository.findByEmail(targetEmail)
            } shouldBe null
        }
    }
    "findByIdOrNull" - {
        "해당하는 id가 없을 경우 널값을 반환한다." - {
            // given
            val id: Long = -1

            // when
            val result = withContext(Dispatchers.IO) {
                accountApiRepository.findByIdOrNull(id) ?: null
            }

            // then
            result shouldBe null
        }
        "해당하는 id가 있을경우 account 반환한다." - {
            // given
            val fromCode = AccountRoles.fromCode("USER")
            val account = Account.create("a@a.com", "nickname", "imageId", fromCode)
            accountApiRepository.save(account)

            // when
            val result =
                withContext(Dispatchers.IO) {
                    accountApiRepository.findByIdOrNull(account.id) ?: null
                }

            // then
            result?.nickname shouldBe account.nickname
            result?.imageId shouldBe account.imageId
            result?.email shouldBe account.email
        }
    }
})
