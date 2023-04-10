package team.guin.team.dto

import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.TemplateType

data class TeamTemplateResponse(
    val type: TemplateType,
    val question: String,
    val options: String?,
) {
    companion object {
        fun create(
            template: TeamTemplate,
        ): TeamTemplateResponse {
            return TeamTemplateResponse(
                type = template.type,
                question = template.question,
                options = template.options,
            )
        }
    }
}
