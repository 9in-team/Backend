package team.guin.team.dto

import team.guin.domain.team.dto.TeamRoleCreate

data class TeamCreateRoleRequest(
    val roleName: String,
    val roleRequired: Int,
) {
    fun toDomain(): TeamRoleCreate {
        return TeamRoleCreate(
            roleName = roleName,
            roleRequired = roleRequired,
        )
    }
}
