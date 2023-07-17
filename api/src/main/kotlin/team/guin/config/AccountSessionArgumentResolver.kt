package team.guin.config

import org.springframework.core.MethodParameter
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import team.guin.config.annotation.AccountSession
import team.guin.login.dto.AccountProfile

@Component
class AccountSessionArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AccountSession::class.java) &&
            parameter.parameterType == AccountProfile::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val accountSessionAnnotation = parameter.getParameterAnnotation(AccountSession::class.java)
        val loginRequired = accountSessionAnnotation?.loginRequired ?: true

        val accountProfile = webRequest.getAttribute("USER", RequestAttributes.SCOPE_SESSION) as? AccountProfile
        if (accountProfile == null && loginRequired) {
            throw BadCredentialsException("로그인을 먼저 해주세요.")
        }

        return accountProfile
    }
}
