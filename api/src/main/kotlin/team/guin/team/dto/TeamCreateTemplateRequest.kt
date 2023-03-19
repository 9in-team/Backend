package team.guin.team.dto

import team.guin.domain.team.dto.TeamTemplateCreate
import team.guin.domain.team.enumeration.TemplateType

data class TeamCreateTemplateRequest(
    val type: TemplateType,
    val question: String,
    val options: List<String>? = null,
) {
    fun toDomain(): TeamTemplateCreate {
        return TeamTemplateCreate(
            templateType = type,
            templateQuestion = question,
            options = options,
        )
    }
}
