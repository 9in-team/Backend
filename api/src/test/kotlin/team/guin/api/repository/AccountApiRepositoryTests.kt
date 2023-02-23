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
        "일치하는 이메일이 존재할 때 일치하는 Account를 반환한다" {
            // given
            val targetEmail = "email@a.com"
            val user = Account.create(targetEmail, "nickname", "imageId")
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
        "일치하는 이메일이 없을 때 null을 반환한다" {
            // given
            val targetEmail = "test@naver.com"

            // when
            val result = withContext(Dispatchers.IO) {
                accountRepository.findByEmail(targetEmail)
            }

            // then
            result shouldBe null
        }
    }
})
