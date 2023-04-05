package team.guin.team.dto

import team.guin.domain.team.TeamPart

data class TeamCreatePartsRequest(
    val roleName: String,
    val roleRequired: Int,
) {
    fun toDomain(): TeamPart {
        return TeamPart.create(
            name = roleName,
            roleRequired = roleRequired,
        )
    }
}
