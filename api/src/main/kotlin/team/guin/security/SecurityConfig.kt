package team.guin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import team.guin.security.kakao.KakaoAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val kakaoAuthenticationFilter: KakaoAuthenticationFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .antMatchers("/api/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilter(kakaoAuthenticationFilter)
            .build()
    }
}
