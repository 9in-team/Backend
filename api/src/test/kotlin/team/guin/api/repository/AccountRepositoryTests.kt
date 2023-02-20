package team.guin.api.repository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import team.guin.domain.Account
import team.guin.domain.enumeration.account.AccountRoles

@SpringBootTest
class AccountRepositoryTests(
    private val accountRepository: AccountRepository,
) : FreeSpec({
    "findByEmail" - {
        "이메일로 엔티티를 찾아올 수 있다" {
            // given
            val targetEmail = "email@a.com"
            val fromCode = AccountRoles.fromCode("USER")
            val user = Account.create(targetEmail, "nickname", "imageId", fromCode)
            withContext(Dispatchers.IO) {
                accountRepository.save(user)
            }

            // when
            val result =
                withContext(Dispatchers.IO) {
                    accountRepository.findByEmail(targetEmail)
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
                accountRepository.findByEmail(targetEmail)
            } shouldBe null
        }
    }
})
