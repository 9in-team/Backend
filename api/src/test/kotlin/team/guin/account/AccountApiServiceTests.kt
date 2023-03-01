package team.guin.account

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import team.guin.domain.account.Account

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
                val optAccountEntity = accountApiRepository.findById(account.id)
                optAccountEntity.isEmpty shouldBe false

                val accountEntity = optAccountEntity.get()
                accountEntity.email shouldBe email
                accountEntity.nickname shouldBe nickname
                accountEntity.imageId shouldBe imageId
            }
        }
    }
    "updateInfo" - {
        "회원이 정보를 변경하면 정보를 업데이트 한다." - {
            // given
            val email = "a@a.com"
            val nickname = "nickname"
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
            findUpdateAccount.imageId shouldBe changeImageId
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
})
