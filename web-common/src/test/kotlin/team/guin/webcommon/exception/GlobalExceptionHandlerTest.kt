import com.fasterxml.jackson.core.JsonParseException
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.springframework.core.MethodParameter
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import team.guin.webcommon.exception.CommonException
import team.guin.webcommon.exception.CommonExceptionCode
import team.guin.webcommon.exception.GlobalExceptionHandler
import team.guin.webcommon.exception.response.ErrorResponse
import java.time.LocalDateTime.now

class GlobalExceptionHandlerTest : FreeSpec(
    {
        "handleCommonException" - {
            "중복된 이메일 CommonException 발생하면 GlobalException 포맷으로 변경한다. " {
                // given
                val globalExceptionHandler = GlobalExceptionHandler()
                val commonException = CommonException(CommonExceptionCode.DUPLICATE_ACCOUNT_EMAIL)

                // when
                val result = globalExceptionHandler.handleCommonException(commonException)

                // then
                result should beInstanceOf(ResponseEntity::class)
                result.statusCode.is4xxClientError shouldBe true
                result.body?.error shouldBe "중복된 이메일입니다. 다시 입력해주세요."
                result.body?.timeStamp?.isBefore(now())
            }
        }

        "handleHttpMessageNotReadableException" - {
            "Validation 실패해 handleHttpMessageNotReadableException가 발생하면 CommonResponse로 반환된다." {
                // given
                val globalExceptionHandler = GlobalExceptionHandler()
                val exception = mockk<HttpMessageNotReadableException>()
                every { exception.cause } returns JsonParseException(null, "올바른 JSON 형식이 아닙니다.")

                // when
                val result: ResponseEntity<ErrorResponse> =
                    globalExceptionHandler.handleHttpMessageNotReadableException(exception)

                // then
                result shouldBe beInstanceOf(ResponseEntity::class)
                result.statusCodeValue shouldBe 400
                result.body?.error shouldBe "올바른 JSON 형식이 아닙니다."
                result.body?.timeStamp?.isBefore(now())
            }
        }
        "handleMethodArgumentNotValidException" - {
            "필드 검증 실패 시 올바른 에러 메시지로 반환한다." {
                // given
                val globalExceptionHandler = GlobalExceptionHandler()
                val fieldError1 = FieldError("objectName", "field1", "Error Message 1")
                val fieldError2 = FieldError("objectName", "field2", "Error Message 2")
                val bindingResult = createBindingResult(fieldError1, fieldError2)
                val exception = MethodArgumentNotValidException(
                    createMockMethodParameter(),
                    bindingResult,
                )

                // when
                val result: ResponseEntity<ErrorResponse> =
                    globalExceptionHandler.handleMethodArgumentNotValidException(exception)

                // then
                result shouldBe beInstanceOf(ResponseEntity::class)
                result.statusCodeValue shouldBe 400
                result.body?.error shouldBe "Validation Failed"
                val errors = result.body?.message
                result.body?.timeStamp?.isBefore(now())
                errors?.size shouldBe 2
                errors?.get("field1") shouldBe "Error Message 1"
                errors?.get("field2") shouldBe "Error Message 2"
            }
        }
    },
)

fun createBindingResult(vararg errors: FieldError): BindingResult {
    val bindingResult: BindingResult = mockk()
    every { bindingResult.allErrors } returns errors.toList()
    return bindingResult
}

fun createMockMethodParameter(): MethodParameter {
    return mockk()
}
