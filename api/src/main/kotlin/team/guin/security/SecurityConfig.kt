package team.guin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import team.guin.domain.account.enumeration.AccountRole
import team.guin.security.kakao.KakaoAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val kakaoAuthenticationFilter: KakaoAuthenticationFilter,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/api/**").hasRole(AccountRole.USER.toString())
            .antMatchers("/api/admin").hasRole(AccountRole.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .addFilter(kakaoAuthenticationFilter)
            .build()
    }
}
