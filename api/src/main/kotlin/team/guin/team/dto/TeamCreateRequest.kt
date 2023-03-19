package team.guin.team.dto

import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.HashTagType

data class TeamCreateRequest(
    val subject: String,
    val content: String,
    val messengerLink: String,
    val teamTemplates: List<TeamCreateTemplateRequest>,
    val hashTags: List<String>,
    val type: HashTagType,
    val teamRoles: List<TeamCreateRoleRequest>,
) {
    fun toDomain(): TeamCreate {
        return TeamCreate(
            teamSubject = subject,
            teamContent = content,
            teamTemplates = teamTemplates.map { it.toDomain() },
            teamOpenChatUrl = messengerLink,
            hashTags = hashTags,
            hashtagType = type,
            teamRoles = teamRoles.map { it.toDomain() },
        )
    }
}
