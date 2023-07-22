package team.guin.team.dto

import com.fasterxml.jackson.annotation.JsonFormat
import team.guin.domain.team.Team
import team.guin.domain.team.dto.AttributeDetail
import team.guin.domain.team.enumeration.SubjectType
import java.time.LocalDateTime

data class TeamListDetail(
    val leaderName: String,
    val subject: String,
    val subjectType: SubjectType,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime,
    val tagTypes: List<AttributeDetail>,
) {
    companion object {
        fun toDetailList(teams: List<Team>): List<TeamListDetail> {
            return teams.map {
                TeamListDetail(
                    it.leader.nickname,
                    it.subject,
                    it.subjectType,
                    it.createdAt,
                    AttributeDetail.create(it.hashTags),
                )
            }
        }
    }
}
