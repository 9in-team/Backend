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
import team.guin.webcommon.exception.ErrorMessage
import team.guin.webcommon.exception.GlobalExceptionHandler
import team.guin.webcommon.exception.response.ErrorResponse
import java.time.LocalDateTime.now

class GlobalExceptionHandlerTest : FreeSpec(
    {
        "handleCommonException" - {
            "중복된 이메일 데이터가 들어오면 에러를 반환한다. " {
                // given
                val globalExceptionHandler = GlobalExceptionHandler()
                val commonException = CommonException(CommonExceptionCode.DUPLICATE_ACCOUNT_EMAIL)

                // when
                val result = globalExceptionHandler.handleCommonException(commonException)

                // then
                result should beInstanceOf(ResponseEntity::class)
                result.statusCode.is4xxClientError shouldBe true
                result.body?.error shouldBe "중복된 이메일입니다."
                result.body?.timeStamp?.isBefore(now())
            }
        }

        "handleHttpMessageNotReadableException" - {
            "유효성 검증에 실패하며 \"올바른 Json형식이 아닙니다\" 를 반환한다." {
                // given
                val globalExceptionHandler = GlobalExceptionHandler()
                val exception = mockk<HttpMessageNotReadableException>()
                every { exception.cause } returns JsonParseException(null, ErrorMessage.INVALID_JSON_FORMAT.message)

                // when
                val result: ResponseEntity<ErrorResponse> =
                    globalExceptionHandler.handleHttpMessageNotReadableException(exception)

                // then
                result shouldBe beInstanceOf(ResponseEntity::class)
                result.statusCodeValue shouldBe 400
                result.body?.error shouldBe ErrorMessage.INVALID_JSON_FORMAT.message
                result.body?.timeStamp?.isBefore(now())
            }
        }
    },
)
