package team.guin.team

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.account.AccountApiRepository
import team.guin.domain.team.Team
import team.guin.domain.team.dto.TeamCreate

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val accountApiRepository: AccountApiRepository,
) {
    @Transactional
    fun createTeam(accountId: Long, teamCreate: TeamCreate): Team {
        val account = accountApiRepository.findByIdOrNull(accountId) ?: throw IllegalArgumentException("유저 없습니다.")
        val team = Team.create(
            account,
            subject = teamCreate.subject,
            content = teamCreate.content,
            openChatUrl = teamCreate.openChatUrl,
            templates = teamCreate.templates,
            roles = teamCreate.roles,
            hashTags = teamCreate.hashTags.toMutableList(),
        )
        return teamApiRepository.save(team)
    }
}
