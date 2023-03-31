package team.guin.domain.team.dto

import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType

data class TeamCreate(
    val subject: String,
    val content: String,
    val openChatUrl: String,
    val templates: List<TeamTemplate>,
    val type: SubjectType,
    val hashTags: List<TagType>,
    val roles: List<TeamRole>,
)
