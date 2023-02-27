package team.guin.example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.domain.exception.CommonException
import team.guin.domain.exception.CommonExceptionCode

@RestController
@RequestMapping("/domain/example")
class ExampleDomainController {
    @GetMapping("/exception")
    fun returnException(): CommonException = throw CommonException(CommonExceptionCode.DUPLICATE_EMAIL)
}
