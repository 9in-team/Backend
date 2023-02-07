package team.guin.monolithic.application.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import team.guin.monolithic.infrastructure.kakao.service.KakaoApiService
import team.guin.monolithic.application.security.filter.JWTAuthenticationFilter
import team.guin.monolithic.application.security.filter.JWTAuthorizationFilter
import team.guin.monolithic.application.security.service.TokenProvider
import team.guin.monolithic.application.user.service.CustomAuthenticationManager

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebConfig(
    val securityProperties: SecurityProperties,
    val authenticationManager: CustomAuthenticationManager,
    val tokenProvider: TokenProvider,
    val kakaoApiService: KakaoApiService,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .cors().and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
            .and()
            .authorizeHttpRequests()
            .antMatchers("/login", "/join").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .antMatchers("/api/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilter(JWTAuthenticationFilter(authenticationManager, securityProperties, tokenProvider, kakaoApiService))
            .addFilter(JWTAuthorizationFilter(authenticationManager, securityProperties, tokenProvider))
            .build()
    }
}