package team.guin.team.dto

import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.HashTagType

data class TeamCreateRequest(
    val type: HashTagType,
    val subject: String,
    val hashTags: MutableList<String>,
    val teamCreateRoles: MutableList<TeamCreateRoleRequest>,
    val content: String,
    val teamTemplate: MutableList<TeamCreateTemplateRequest>,
    val messengerLink: String,
) {
    fun toDomain(): TeamCreate {
        return TeamCreate(
            type = this.type,
            subject = this.subject,
            hashTags = this.hashTags,
            content = this.content,
            messengerLink = this.messengerLink,
        )
    }
}
