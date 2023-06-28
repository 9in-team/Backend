package team.guin.login.apple.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet
import team.guin.login.apple.dto.Payload

class AppleLoginUtil {
    companion object {
        fun getPayload(idToken: String): Payload {
            val signedJWT: SignedJWT = SignedJWT.parse(idToken)
            val getPayload: ReadOnlyJWTClaimsSet = signedJWT.jwtClaimsSet

            return ObjectMapper().readValue(getPayload.toJSONObject().toJSONString(), Payload::class.java)
        }
    }
}
