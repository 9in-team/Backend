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

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamRoleApiRepository: TeamRoleApiRepository,
    private val hashTagApiRepository: HashTagApiRepository,
    private val templateOptionApiRepository: TemplateOptionApiRepository,
    private val teamHashTagApiRepository: TeamHashTagApiRepository,

) {

    fun createTeam(
        accountId: Long,
        teamCreate: TeamCreate,
    ): Team {
        val account = accountApiRepository.findByIdOrNull(accountId) ?: throw IllegalArgumentException("유저 없음")
        val team = Team.create(
            account = account,
            teamSubject = teamCreate.teamSubject,
            teamContent = teamCreate.teamContent,
            teamOpenChatUrl = teamCreate.teamOpenChatUrl,
        )

        val roles = teamCreate.teamRoles.map {
            TeamRole.create(
                team = team,
                roleName = it.roleName,
                roleRequired = it.roleRequired,
            )
        }
        val teamHashTag: MutableList<TeamHashTag> = mutableListOf()

        val hashTags = teamCreate.hashTags.map {
            val hashTag = HashTag.create(
                hashTagName = it,
                hashTagType = teamCreate.hashtagType,
            )
            teamHashTag.add(TeamHashTag(hashTag, team))
            hashTag
        }
        var options: MutableList<TemplateOption> = mutableListOf()
        val templates = teamCreate.teamTemplates.map {
            val template = TeamTemplate.create(
                team = team,
                templateType = it.templateType,
                templateQuestion = it.templateQuestion,
            )
            if (it.options != null) {
                options = it.options!!.map { TemplateOption(template, it) }.toMutableList()
            }
            template
        }

        team.teamTemplates?.addAll(templates)

        val savedTeam = teamApiRepository.save(team) // 팀 생성
        teamRoleApiRepository.saveAll(roles)
        hashTagApiRepository.saveAll(hashTags)
        teamHashTagApiRepository.saveAll(teamHashTag)
        templateOptionApiRepository.saveAll(options)
        return savedTeam
    }
}
