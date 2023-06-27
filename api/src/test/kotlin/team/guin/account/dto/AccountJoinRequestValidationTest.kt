package team.guin.account.dto

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator

class AccountJoinRequestValidationTest : FreeSpec({

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    "accessToken" - {
        "accessToken이 공백이면 '@NotBlank' 제약 조건을 위배한다" {
            // given
            val accountJoinRequest = AccountJoinRequest("")

            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accountJoinRequest)

            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "accessToken은 필수값입니다." } shouldBe true
        }

        "accessToken이 비어있지 않으면 유효성 검사를 통과한다" {
            // given
            val accountJoinRequest = AccountJoinRequest("valid-access-token")

            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accountJoinRequest)

            // then
            violations.isEmpty() shouldBe true
        }
    }
})
