package team.guin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import team.guin.domain.account.enumeration.AccountRole

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .sessionManagement().and()
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/**").permitAll()
            .antMatchers("/api/**").hasRole(AccountRole.USER.toString())
            .antMatchers("/api/admin").hasRole(AccountRole.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .build()
    }
}
