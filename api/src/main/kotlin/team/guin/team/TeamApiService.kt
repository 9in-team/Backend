package team.guin.team

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.account.AccountApiRepository
import team.guin.domain.team.Team
import team.guin.domain.team.TeamHashTag
import team.guin.domain.team.dto.TeamCreate

@Service
class TeamApiService(
    private val teamApiRepository: TeamApiRepository,
    private val hashTagApiRepository: HashTagApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamHashTagApiRepository: TeamHashTagApiRepository,
) {
    @Transactional
    fun create(accountId: Long, teamCreate: TeamCreate): Team {
        val account = accountApiRepository.findByIdOrNull(accountId) ?: throw IllegalArgumentException("유저 없습니다.")
        val team = Team.create(
            account,
            subject = teamCreate.subject,
            content = teamCreate.content,
            openChatUrl = teamCreate.openChatUrl,
            templates = teamCreate.templates,
            roles = teamCreate.roles,
        )
        val savedTeam = teamApiRepository.save(team)
        if (teamCreate.hashTags != null) {
            teamCreate.hashTags.map { hashTag ->
                val findHashTag = hashTagApiRepository.findByTypeAndSubjectType(hashTag, teamCreate.type) ?: throw IllegalArgumentException("해시태그 없음")
                teamHashTagApiRepository.save(TeamHashTag.create(savedTeam, findHashTag))
            }
        }
        return savedTeam
    }
}
