package team.guin.account

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountApiServiceTests(
    private val accountApiService: AccountApiService,
    private val accountApiRepository: AccountApiRepository,
) : FreeSpec({
    "join" - {
        "회원정보 입력이 들어오면 엔티티로 만들어 저장한다" {
            // given
            val email = "a@a.com"
            val nickname = "nickname"
            val imageId = "https://imugr.com/example"

            // when
            val account = accountApiService.join(email, nickname, imageId)

            // then
            withContext(Dispatchers.IO) {
                val optAccountEntity = accountApiRepository.findById(account.userId)
                optAccountEntity.isEmpty shouldBe false

                val accountEntity = optAccountEntity.get()
                accountEntity.email shouldBe email
                accountEntity.nickname shouldBe nickname
                accountEntity.imageId shouldBe imageId
            }
        }
    }
})
