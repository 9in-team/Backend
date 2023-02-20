package team.guin.api.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import team.guin.api.controller.request.JoinRequest
import team.guin.api.repository.AccountRepository

@SpringBootTest
class AccountServiceTests(
    private val accountService: AccountService,
    private val accountRepository: AccountRepository,
) : FreeSpec({
    "join" - {
        "회원정보 입력이 들어오면 엔티티로 만들어 저장한다" {
            // given
            val joinRequest = JoinRequest("id@a.com", "nickname", "https://imgur.com/abcde")

            // when
            val account = accountService.join(joinRequest)

            // then
            withContext(Dispatchers.IO) {
                accountRepository.existsById(account.userId)
            } shouldBe true
        }
    }
})
