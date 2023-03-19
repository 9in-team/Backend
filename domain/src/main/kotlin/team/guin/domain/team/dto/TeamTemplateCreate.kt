package team.guin.domain.team.dto

import team.guin.domain.team.enumeration.TemplateType

data class TeamTemplateCreate(val templateType: TemplateType, val templateQuestion: String, val options: List<String>? = null)
