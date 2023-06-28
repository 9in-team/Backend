package team.guin.login.apple.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet
import org.springframework.stereotype.Component
import team.guin.login.apple.dto.Payload

@Component
class AppleLoginUtil(
    private val objectMapper: ObjectMapper
) {
    fun getPayload(idToken: String): Payload {
        val signedJWT: SignedJWT = SignedJWT.parse(idToken)
        val getPayload: ReadOnlyJWTClaimsSet = signedJWT.jwtClaimsSet

        return objectMapper.readValue(getPayload.toJSONObject().toJSONString(), Payload::class.java)
    }
}
