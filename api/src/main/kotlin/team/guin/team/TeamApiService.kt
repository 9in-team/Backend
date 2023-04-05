package team.guin.team

import org.springframework.stereotype.Service
import team.guin.account.AccountApiService
import team.guin.domain.team.Team
import team.guin.domain.team.dto.TeamCreate

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val accountApiService: AccountApiService,
) {
    fun createTeam(accountId: Long, teamCreate: TeamCreate): Team {
        val account = accountApiService.findById(accountId)
        val team = Team.create(
            leader = account,
            subject = teamCreate.subject,
            content = teamCreate.content,
            openChatUrl = teamCreate.openChatUrl,
            templates = teamCreate.templates,
            parts = teamCreate.parts,
            hashTags = teamCreate.hashTags.toMutableList(),
        )
        return teamApiRepository.save(team)
    }

    fun findById(id: Long): Team {
        return teamApiRepository.findById(id).get()
    }
}
