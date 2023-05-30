package team.guin.domain.team.dto

import team.guin.domain.team.HashTag

data class AttributeDetail(
    val name: String,
    val value: String,
) {
    companion object {
        fun create(hashtags: List<HashTag>): List<AttributeDetail> {
            return hashtags.map { AttributeDetail(it.type.name, it.type.value) }
        }
    }
}
