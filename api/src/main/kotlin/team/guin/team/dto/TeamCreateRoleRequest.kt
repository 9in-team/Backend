package team.guin.team.dto

import team.guin.domain.team.TeamRole

data class TeamCreateRoleRequest(
    val roleName: String,
    val roleRequired: Int,
) {
    fun toDomain(): TeamRole {
        return TeamRole.create(
            name = roleName,
            roleRequired = roleRequired,
        )
    }
}
