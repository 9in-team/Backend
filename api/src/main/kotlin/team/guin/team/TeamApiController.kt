package team.guin.team

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.domain.team.enumeration.TagType
import team.guin.team.dto.HashTagDetail
import team.guin.team.dto.TeamCreateDetail
import team.guin.team.dto.TeamCreateRequest

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
}
