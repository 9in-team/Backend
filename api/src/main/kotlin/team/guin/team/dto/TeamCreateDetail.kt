package team.guin.team.dto

import team.guin.domain.team.Team
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType

data class TeamCreateDetail(
    val teamId: Long,
    val openChatUrl: String,
    val content: String,
    val subject: String,
    val teamTemplates: List<TeamTemplateResponse>,
    val types: List<TagType>,
    val subjectType: SubjectType,
    val roles: List<TeamRoleResponse>,
) {
    companion object {

        fun create(
            team: Team,
        ): TeamCreateDetail {
            return TeamCreateDetail(
                teamId = team.id,
                openChatUrl = team.openChatUrl,
                content = team.content,
                subject = team.subject,
                subjectType = team.hashTags[0].let {
                    it.subjectType
                },
                teamTemplates = team.templates.map { TeamTemplateResponse.create(it) },
                types = team.hashTags.map { it.type },
                roles = team.roles.map { TeamRoleResponse.create(it) },
            )
        }
    }
}
