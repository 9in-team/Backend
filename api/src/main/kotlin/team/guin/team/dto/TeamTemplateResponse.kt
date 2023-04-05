package team.guin.team.dto

import team.guin.domain.team.TemplateOption
import team.guin.domain.team.enumeration.TemplateType

data class TeamTemplateResponse(
    val type: TemplateType,
    val question: String,
    val options: List<String>,
) {
    companion object {
        fun entityToResponse(
            type: TemplateType,
            question: String,
            options: List<TemplateOption>,
        ): TeamTemplateResponse {
            return TeamTemplateResponse(
                type = type,
                question = question,
                options = options.map { it.name },
            )
        }
    }
}
