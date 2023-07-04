package team.guin

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.persistence.EntityManager

@SpringBootApplication
@EnableJpaAuditing
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

@Bean
fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory = JPAQueryFactory(entityManager)
