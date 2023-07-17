package team.guin.login.apple.dto

data class AppleLoginRequest(
    val state: String?,
    val code: String?,
    val id_token: String,
    val user: String?,
)
