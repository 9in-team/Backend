package team.guin.api.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.domain.User

@SpringBootTest
class UserRepositoryTests(
    private val userRepo: UserRepository,
) : FreeSpec({
    "findByEmail" - {
        "이메일로 엔티티를 찾아올 수 있다" {
            // given
            val targetEmail = "email@a.com"
            val user = User(0, targetEmail, "nickname", "imageId", "USER")
            userRepo.save(user)

            // when
            val result = userRepo.findByEmail(targetEmail)

            // then
            result?.nickname shouldBe "nickname"
            result?.imageId shouldBe "imageId"
        }
    }
})
