package team.guin.config.annotation

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("USER")
annotation class AccountSession
