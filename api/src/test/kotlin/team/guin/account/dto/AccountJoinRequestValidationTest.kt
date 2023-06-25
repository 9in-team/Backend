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
            val accounJoinRequest = AccountJoinRequest("")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "공백일 수 없습니다" } shouldBe true
        }
    }
})
