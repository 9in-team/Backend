package team.guin.team.dto

import team.guin.domain.team.HashTag
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType

data class TeamCreateRequest(
    val subject: String,
    val content: String,
    val messengerLink: String,
    val teamTemplates: List<TeamCreateTemplateRequest>,
    val hashTags: List<TagType>,
    val subjectType: SubjectType,
    val teamRoles: List<TeamCreateRoleRequest>,
) {
    fun toDomain(): TeamCreate {
        return TeamCreate(
            subject = subject,
            content = content,
            templates = teamTemplates.map { it.toDomain() },
            openChatUrl = messengerLink,
            hashTags = hashTags.map { HashTag.create(subjectType = subjectType, it) },
            roles = teamRoles.map { it.toDomain() },
        )
    }
}
