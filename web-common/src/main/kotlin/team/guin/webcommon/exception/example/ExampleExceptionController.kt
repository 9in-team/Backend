package team.guin.webcommon.exception.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.webcommon.exception.CommonException
import team.guin.webcommon.exception.CommonExceptionCode

@RestController
@RequestMapping("/common/example/exception/")
class ExampleExceptionController {
    @GetMapping("duplicate")
    fun returnDuplicateException(): CommonException = throw CommonException(CommonExceptionCode.DUPLICATE_ACCOUNT_EMAIL)
}
