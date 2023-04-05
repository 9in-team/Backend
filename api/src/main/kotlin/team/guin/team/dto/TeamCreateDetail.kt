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
    val hashTags: List<TagType>,
    val subjectType: SubjectType,
    val parts: List<TeamPartResponse>,
) {
    companion object {

        fun toResponse(
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
                teamTemplates = team.templates.map { TeamTemplateResponse.entityToResponse(it.type, it.question, it.options) },
                hashTags = team.hashTags.map { it.type },
                parts = team.parts.map { TeamPartResponse.entityToResponse(it.name, it.requiredCount, it.hiredCount) },
            )
        }
    }
}
