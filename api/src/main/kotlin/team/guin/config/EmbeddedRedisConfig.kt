package team.guin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Profile("local")
@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.redis.port}")
    private val redisPort: Int,
) {
    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun redisServer() {
        redisServer = RedisServer(redisPort)
        redisServer.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }
}
