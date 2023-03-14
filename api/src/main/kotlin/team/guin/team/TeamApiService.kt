package team.guin.team

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team.guin.account.AccountApiRepository
import team.guin.domain.team.HashTag
import team.guin.domain.team.Team
import team.guin.domain.team.TeamHashTag
import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.TemplateOption
import team.guin.domain.team.dto.TeamCreate
import team.guin.team.dto.TeamCreateRoleRequest
import team.guin.team.dto.TeamCreateTemplateRequest

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamRoleApiRepository: TeamRoleApiRepository,
    private val teamTemplateApiRepository: TeamTemplateApiRepository,
    private val hashTagApiRepository: HashTagApiRepository,
    private val templateOptionApiRepository: TemplateOptionApiRepository,
    private val teamHashTagApiRepository: TeamHashTagApiRepository,

) {

    fun createTeam(
        accountId: Long,
        teamCreate: TeamCreate,
        teamCreateRoles: MutableList<TeamCreateRoleRequest>,
        teamCreateTemplateRequests: MutableList<TeamCreateTemplateRequest>,
    ): Team {
        val account = accountApiRepository.findByIdOrNull(accountId) ?: throw IllegalArgumentException("유저 없음")

        val savedTeam = teamApiRepository.save(Team(account, teamCreate.subject, teamCreate.content, teamCreate.messengerLink))
        val teamRoles = teamCreateRoles.map { TeamRole(team = savedTeam, roleName = it.roleName, roleRequired = it.roleRequired) }.toList()
        teamRoleApiRepository.saveAll(teamRoles)
        var templateOptionList: MutableList<TemplateOption> = mutableListOf()
        var teamTemplateList: MutableList<TeamTemplate> = mutableListOf()
        for (teamCreateTemplateRequest in teamCreateTemplateRequests) {
            val teamTemplate = TeamTemplate(savedTeam, teamCreateTemplateRequest.type, teamCreateTemplateRequest.question)
            teamTemplateList.add(teamTemplate)
            if (teamCreateTemplateRequest.options != null) {
                teamCreateTemplateRequest.options.forEach { templateOptionList.add(TemplateOption(teamTemplate, it)) }
            }
        }
        teamTemplateApiRepository.saveAll(teamTemplateList)
        templateOptionApiRepository.saveAll(templateOptionList)
        val hashTags = teamCreate.hashTags.map { HashTag(it, teamCreate.type) }.toList()
        hashTagApiRepository.saveAll(hashTags)
        val teamHashTagList = hashTags.map { TeamHashTag(it, savedTeam) }.toList()
        teamHashTagApiRepository.saveAll(teamHashTagList)
        return savedTeam
    }
}
