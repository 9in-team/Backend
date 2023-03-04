package team.guin.account

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
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

    "delete" - {
        "탈퇴한 유저는 조회되지 않는다" - {
            // given
            val savedAccount1 =
                accountApiRepository.save(Account.create("t1@t.com", "test1", "https://htt1.co.kr"))
            val savedAccount2 =
                accountApiRepository.save(Account.create("t2@t.com", "test2", "https://htt2.co.kr"))
            val savedAccount3 =
                accountApiRepository.save(Account.create("t3@t.com", "test3", "https://htt3.co.kr"))

            // when
            accountApiService.delete(savedAccount3.id)

            // then
            val accounts = accountApiRepository.findAll()
            accounts.find { it.nickname == savedAccount1.nickname } shouldNotBe null
            accounts.find { it.email == savedAccount1.email } shouldNotBe null
            accounts.find { it.imageId == savedAccount1.imageId } shouldNotBe null
            accounts.find { it.nickname == savedAccount2.nickname } shouldNotBe null
            accounts.find { it.email == savedAccount2.email } shouldNotBe null
            accounts.find { it.imageId == savedAccount2.imageId } shouldNotBe null
            accounts.find { it.nickname == savedAccount3.nickname } shouldBe null
            accounts.find { it.email == savedAccount3.email } shouldBe null
            accounts.find { it.imageId == savedAccount3.imageId } shouldBe null
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
