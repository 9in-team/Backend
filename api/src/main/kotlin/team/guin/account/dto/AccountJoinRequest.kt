package team.guin.account.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class AccountJoinRequest(
    @field:NotBlank
    @field:Email
    val email: String,
    @field:NotBlank
    val nickname: String,
    @field:NotBlank
    val imageId: String,
)
