package team.guin.team.dto

import team.guin.domain.team.TeamRole

data class TeamCreateRoleRequest(
    val name: String,
    val requiredCount: Int,
) {
    fun toDomain(): TeamRole {
        return TeamRole.create(
            name = name,
            requiredCount = requiredCount,
        )
    }
}
