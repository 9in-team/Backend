package team.guin.domain.team.dto

import team.guin.domain.team.HashTag
import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate

data class TeamCreate(
    val subject: String,
    val content: String,
    val openChatUrl: String,
    val templates: List<TeamTemplate>,
    val hashTags: List<HashTag>,
    val roles: List<TeamRole>,
)
