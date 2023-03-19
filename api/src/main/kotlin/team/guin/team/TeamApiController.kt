package team.guin.team

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.team.dto.TeamCreateDetail
import team.guin.team.dto.TeamCreateRequest

@RestController
@RequestMapping("/team")
class TeamApiController(
    private val teamApiService: TeamApiService,
) {

    @PostMapping("{id}")
    fun createTeam(@PathVariable id: Long, @RequestBody teamCreateRequest: TeamCreateRequest): CommonResponse<TeamCreateDetail> {
        val createTeam = teamApiService.createTeam(
            accountId = id,
            teamCreate = teamCreateRequest.toDomain(),
        )
        return CommonResponse.okWithDetail(TeamCreateDetail(createTeam.id))
    }
}
