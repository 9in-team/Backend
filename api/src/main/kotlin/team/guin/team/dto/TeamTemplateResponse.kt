package team.guin.team.dto

import team.guin.domain.team.enumeration.TemplateType

data class TeamTemplateResponse(
    val type: TemplateType,
    val question: String,
    val options: String?,
) {
    companion object {
        fun entityToResponse(
            type: TemplateType,
            question: String,
            options: String?,
        ): TeamTemplateResponse {
            return TeamTemplateResponse(
                type = type,
                question = question,
                options = options,
            )
        }
    }
}
