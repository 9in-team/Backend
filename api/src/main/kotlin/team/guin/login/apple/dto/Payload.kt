package team.guin.login.apple.dto


data class Payload(
    val iss: String?,
    val aud: String?,
    val exp: Long?,
    val iat: Long?,
    val sub: String?,
    val nonce: String?,
    val c_hash: String?,
    val at_hash: String?,
    val email: String,
    val email_verified: String?,
    val is_private_email: String?,
    val auth_time: Long?,
    val isNonce_supported: Boolean?,
)
