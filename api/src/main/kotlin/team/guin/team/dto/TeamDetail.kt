package team.guin.team.dto

import com.fasterxml.jackson.annotation.JsonFormat
import team.guin.domain.team.Team
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import java.time.LocalDateTime

data class TeamDetail(
    val teamId: Long,
    val content: String,
    val subject: String,
    val types: List<TagType>,
    val subjectType: SubjectType,
    val roles: List<TeamRoleDetail>,
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초")
    val createdAt: LocalDateTime,
) {
    companion object {
        fun detail(team: Team): TeamDetail {
            return TeamDetail(
                teamId = team.id,
                content = team.content,
                subject = team.subject,
                types = team.hashTags.map { it.type },
                subjectType = team.subjectType,
                roles = team.roles.map { TeamRoleDetail.create(it) },
                createdAt = team.createdAt,
            )
        }
    }
}
