package team.guin.team.dto

data class TeamCreateRoleRequest(
    val roleName: String,
    val roleRequired: Int,
)
