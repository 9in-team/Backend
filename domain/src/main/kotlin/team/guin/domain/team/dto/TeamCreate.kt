package team.guin.domain.team.dto

import team.guin.domain.team.enumeration.HashTagType

data class TeamCreate(
    val type: HashTagType,
    val subject: String,
    val hashTags: MutableList<String>,
    val content: String,
    var messengerLink: String,
)
