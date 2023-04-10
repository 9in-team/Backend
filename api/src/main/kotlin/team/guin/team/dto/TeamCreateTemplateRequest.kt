package team.guin.team.dto

import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.TemplateType

data class TeamCreateTemplateRequest(
    val type: TemplateType,
    val question: String,
    val options: String?,
) {
    fun toDomain(): TeamTemplate {
        return TeamTemplate(
            type = type,
            question = question,
            options = options,
        )
    }
}
