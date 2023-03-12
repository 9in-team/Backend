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

    "email" - {
        "이메일 형식이 아니면 \"올바른 형식의 이메일 주소여야 합니다\" 을 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("test", "testName", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "올바른 형식의 이메일 주소여야 합니다" } shouldBe true
        }
        "이메일이 공백이면  \"올바른 형식의 이메일 주소여야 합니다\" 을 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("", "testName", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "공백일 수 없습니다" } shouldBe true
        }
    }
    "nickname" - {
        "닉네임이 공백이면  \"공백일 수 없습니다\" 을 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("", "testName", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "공백일 수 없습니다" } shouldBe true
        }
    }
    "imageId" - {
        "이미지 URL이 공백이면  \"공백일 수 없습니다\" 을 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("", "testName", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.isNotEmpty() shouldBe true
            violations.any { it.message == "공백일 수 없습니다" } shouldBe true
        }
    }
})
