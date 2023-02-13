package team.guin.monolithic.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class SimpleRoute(private val handler: ExampleHandler) {
    @Bean
    fun route() = router {
        GET("/hello", handler::hello)
    }
}
