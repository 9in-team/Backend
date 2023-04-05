package team.guin.team.dto

import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.TemplateOption
import team.guin.domain.team.enumeration.TemplateType

data class TeamCreateTemplateRequest(
    val type: TemplateType,
    val question: String,
    val options: List<String> = listOf(),
) {
    fun toDomain(): TeamTemplate {
        return TeamTemplate(
            type = type,
            question = question,
            options = options.map { TemplateOption.create(name = it) }.toMutableList(),
        )
    }
}
