package team.guin.team.dto

import team.guin.domain.team.TeamRole

data class TeamRoleDetail(
    val name: String,
    val requiredCount: Int,
    val hiredCount: Int,
) {
    companion object {
        fun create(
            role: TeamRole,
        ): TeamRoleDetail {
            return TeamRoleDetail(
                name = role.name,
                requiredCount = role.requiredCount,
                hiredCount = role.hiredCount,
            )
        }
    }
}
