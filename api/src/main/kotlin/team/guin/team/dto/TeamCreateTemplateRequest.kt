package team.guin.team.dto

import team.guin.domain.team.enumeration.TemplateType

data class TeamCreateTemplateRequest(
    val type: TemplateType,
    val question: String,
    val options: MutableList<String>? = null,
)
