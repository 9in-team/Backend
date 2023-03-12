package team.guin.account.dto

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import javax.validation.ConstraintViolation

class AccountJoinRequestValidationTest : FreeSpec({

    lateinit var validator: LocalValidatorFactoryBean

    beforeSpec {
        validator = LocalValidatorFactoryBean().apply {
            setValidationMessageSource(
                org.springframework.context.support.ResourceBundleMessageSource().apply {
                    setBasename("classpath:ValidationMessages_ko")
                    setDefaultEncoding("UTF-8")
                },
            )
            afterPropertiesSet()
        }
    }

    "email" - {
        val invalidEmail = listOf("test", "@test.com", "test@.com", "test@test.", "test@.test.")
        invalidEmail.forEach {
            "이메일 형식이 잘못되면 '올바른 형식의 이메일 주소여야 합니다' 메시지를 반환한다 - $it" {
                // given
                val accounJoinRequest = AccountJoinRequest(it, "testName", "imgId")
                // when
                val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
                // then
                violations.size shouldBe 1
                violations.first().messageTemplate shouldBe "{javax.validation.constraints.Email.message}"
            }
        }
        "이메일이 공백이면 '공백일 수 없습니다' 메시지를 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("", "testName", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.size shouldBe 1
            violations.first().messageTemplate shouldBe "{javax.validation.constraints.NotBlank.message}"
        }
    }
    "nickname" - {
        "닉네임이 공백이면 '공백일 수 없습니다' 메시지를 반환한다" {
            // given
            val accounJoinRequest = AccountJoinRequest("test@test.com", "", "imgId")
            // when
            val violations: Set<ConstraintViolation<AccountJoinRequest>> = validator.validate(accounJoinRequest)
            // then
            violations.size shouldBe 1
            violations.first().messageTemplate shouldBe "{javax.validation.constraints.NotBlank.message}"
        }
    }
    "imageId" - {
        "이미지 URL이 공백이면 '공백일 수 없습니다' 메시지를 반환한다" {
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
