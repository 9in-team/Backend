package team.guin.team

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team.guin.account.AccountApiService
import team.guin.domain.team.Team
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.SubjectType

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val accountApiService: AccountApiService,
    private val teamApiQueryDslRepository: TeamApiQueryDslRepository,
) {
    fun createTeam(accountId: Long, teamCreate: TeamCreate): Team {
        val leader = accountApiService.findById(accountId)
        val team = Team.create(
            leader = leader,
            subject = teamCreate.subject,
            content = teamCreate.content,
            openChatUrl = teamCreate.openChatUrl,
            templates = teamCreate.templates,
            roles = teamCreate.roles,
            subjectType = teamCreate.subjectType,
            hashTags = teamCreate.hashTags.toMutableList(),
        )
        return teamApiRepository.save(team)
    }

    fun detail(teamId: Long): Team {
        return findByIdOrNull(teamId)
    }

    fun findByIdOrNull(id: Long): Team {
        return teamApiRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("team 없음")
    }
    fun findAllBySubjectType(subjectType: SubjectType?): List<Team> {
        return teamApiQueryDslRepository.findAllBySubjectType(subjectType)
    }

    fun detail(teamId: Long): Team {
        return getTeam(teamId)
    }

    fun getTeam(id: Long): Team {
        return teamApiRepository.findByIdOrNull(id) ?: throw IllegalStateException("팀이 존재하지 않습니다.")
    }
}
