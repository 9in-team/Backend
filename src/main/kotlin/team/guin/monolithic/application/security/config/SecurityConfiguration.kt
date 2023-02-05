package team.guin.monolithic.application.security.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableConfigurationProperties(
  SecurityProperties::class
)
class SecurityConfiguration(
  val securityProperties: SecurityProperties
) {
  @Bean
  fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder(securityProperties.strength)
}
