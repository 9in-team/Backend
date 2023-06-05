package team.guin.domain.team.dto

import team.guin.domain.team.HashTag
import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.SubjectType

data class TeamCreate(
    val subject: String,
    val content: String,
    val openChatUrl: String,
    val templates: List<TeamTemplate>,
    val subjectType: SubjectType,
    val hashTags: List<HashTag>,
    val roles: List<TeamRole>,
)
