package team.guin.account.dto

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import javax.validation.ConstraintViolation

class AccountJoinRequestValidationTest : FreeSpec({

    lateinit var validator: LocalValidatorFactoryBean

    beforeSpec {
        validator = LocalValidatorFactoryBean()
        validator.afterPropertiesSet()
    }

    "accessToken" - {
        "accessToken이 공백이면  \"공백일수 없습니다.\" 을 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("test@test.com", "testName", "")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.size shouldBe 1
            violations.first().messageTemplate shouldBe "{javax.validation.constraints.NotBlank.message}"
        }
    }
})
