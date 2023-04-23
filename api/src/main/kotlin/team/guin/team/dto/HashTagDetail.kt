package team.guin.team.dto

import team.guin.domain.team.dto.AttributeDetail

data class HashTagDetail(
    val project: List<AttributeDetail>,
    val study: List<AttributeDetail>,
) {
    companion object {
        fun create(
            project: List<AttributeDetail>,
            study: List<AttributeDetail>,
        ): HashTagDetail {
            return HashTagDetail(
                project = project,
                study = study,
            )
        }
    }
}
