package team.guin.team.dto

import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.TemplateType

data class TeamTemplateDetail(
    val type: TemplateType,
    val question: String,
    val options: String?,
) {
    companion object {
        fun create(
            template: TeamTemplate,
        ): TeamTemplateDetail {
            return TeamTemplateDetail(
                type = template.type,
                question = template.question,
                options = template.options,
            )
        }
    }
}
