package team.guin.team

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.config.annotation.AccountSession
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import team.guin.login.dto.AccountProfile
import team.guin.team.dto.HashTagDetail
import team.guin.team.dto.TeamCreateDetail
import team.guin.team.dto.TeamCreateRequest
import team.guin.team.dto.TeamDetail
import team.guin.team.dto.TeamListDetail

@RestController
@RequestMapping("/team")
class TeamApiController(
    private val teamApiService: TeamApiService,
) {
    @PostMapping
    fun create(@AccountSession accountProfile: AccountProfile, @RequestBody teamCreateRequest: TeamCreateRequest): CommonResponse<TeamCreateDetail> {
        val team = teamApiService.createTeam(accountProfile.id, teamCreateRequest.toDomain())
        return CommonResponse.okWithDetail(TeamCreateDetail.create(team))
    }

    @GetMapping("/hashtag")
    fun createHashTag(): CommonResponse<HashTagDetail> {
        return CommonResponse.okWithDetail(
            HashTagDetail.create(
                project = TagType.projectTag(),
                study = TagType.studyTag(),
            ),
        )
    }

    @GetMapping
    fun teamList(@RequestParam subjectType: SubjectType?): CommonResponse<List<TeamListDetail>> {
        val teams = teamApiService.findAllBySubjectType(subjectType)
        return CommonResponse.okWithDetail(TeamListDetail.toDetailList(teams))
    }

    @GetMapping("{teamId}")
    fun detail(@PathVariable teamId: Long): CommonResponse<TeamDetail> {
        val team = teamApiService.detail(teamId)
        return CommonResponse.okWithDetail(TeamDetail.detail(team))
    }
}
