package team.guin.domain.team.dto

import team.guin.domain.team.enumeration.HashTagType

data class TeamCreate(
    val teamSubject: String,
    val teamContent: String,
    val teamOpenChatUrl: String,
    val teamTemplates: List<TeamTemplateCreate>,
    val hashTags: List<String>,
    val hashtagType: HashTagType,
    val teamRoles: List<TeamRoleCreate>,
    // val teamRole: List<TeamRole>,
)
