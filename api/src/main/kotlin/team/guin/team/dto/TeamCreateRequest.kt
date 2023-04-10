package team.guin.team.dto

import team.guin.domain.team.HashTag
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType

data class TeamCreateRequest(
    val subject: String,
    val content: String,
    val openChatUrl: String,
    val teamTemplates: List<TeamCreateTemplateRequest>,
    val types: List<TagType>,
    val subjectType: SubjectType,
    val roles: List<TeamCreateRoleRequest>,
) {
    fun toDomain(): TeamCreate {
        return TeamCreate(
            subject = subject,
            content = content,
            templates = teamTemplates.map { it.toDomain() },
            openChatUrl = openChatUrl,
            hashTags = types.map { HashTag.create(subjectType = subjectType, it) },
            roles = roles.map { it.toDomain() },
        )
    }
}
