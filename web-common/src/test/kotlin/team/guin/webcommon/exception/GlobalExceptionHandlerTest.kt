package team.guin.webcommon.exception

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.springframework.http.ResponseEntity

class GlobalExceptionHandlerTest : FreeSpec({
    "handleHttpMessageNotReadableException" - {
        "중복된 이메일 CommonException 발생하면 GlobalException 포맷으로 변경한다. " {
            // given
            val globalExceptionHandler = GlobalExceptionHandler()
            val commonException = CommonException(CommonExceptionCode.DUPLICATE_EMAIL)

            // when
            val result = globalExceptionHandler.handleCommonException(commonException)

            // then
            result should beInstanceOf(ResponseEntity::class)
            result.statusCode.is4xxClientError shouldBe true
            result.body?.error shouldBe "중복된 이메일입니다. 다시 입력해주세요."
        }
    }
})
