package team.guin.api.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
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
            val user = Account(0, targetEmail, "nickname", "imageId", fromCode)
            accountRepository.save(user)

            // when
            val result = accountRepository.findByEmail(targetEmail)

            // then
            result?.nickname shouldBe "nickname"
            result?.imageId shouldBe "imageId"
        }
    }
})
