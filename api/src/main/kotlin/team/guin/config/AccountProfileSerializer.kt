package team.guin.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.SerializationException
import team.guin.security.kakao.AccountProfile

class AccountProfileSerializer(val objectMapper: ObjectMapper) : RedisSerializer<AccountProfile> {
    override fun serialize(t: AccountProfile?): ByteArray {
        return GenericJackson2JsonRedisSerializer(objectMapper).serialize(t)
    }

    override fun deserialize(bytes: ByteArray?): AccountProfile? {
        if (bytes == null || bytes.isEmpty()) {
            return null
        }
        return try {
            objectMapper.readValue(bytes, AccountProfile::class.java)
        } catch (ex: Exception) {
            throw SerializationException("Could not read JSON: " + ex.message, ex)
        }
    }
}
