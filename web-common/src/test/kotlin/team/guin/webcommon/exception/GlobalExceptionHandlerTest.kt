import com.fasterxml.jackson.core.JsonParseException
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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
    },
)
