package team.guin.team

import org.springframework.web.bind.annotation.*
import team.guin.common.CommonResponse
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import team.guin.team.dto.*

@RestController
@RequestMapping("/team")
class TeamApiController(
    private val teamApiService: TeamApiService,
) {
    @PostMapping("/{accountId}")
    fun create(@PathVariable("accountId") accountId: Long, @RequestBody teamCreateRequest: TeamCreateRequest): CommonResponse<TeamCreateDetail> {
        val team = teamApiService.createTeam(accountId, teamCreateRequest.toDomain())
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
