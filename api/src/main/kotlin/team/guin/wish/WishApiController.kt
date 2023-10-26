package team.guin.wish

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.common.CommonResponse
import team.guin.config.annotation.AccountSession
import team.guin.login.dto.AccountProfile

@RestController
@RequestMapping("/wish")
class WishApiController(
    private val wishApiService: WishApiService,
) {
    @PostMapping
    fun create(@AccountSession accountProfile: AccountProfile, @RequestBody wishCreateRequest: WishCreateRequest): CommonResponse<WishCreateDetail> {
        val wish = wishApiService.create(accountProfile.id, wishCreateRequest)
        return CommonResponse.okWithDetail(WishCreateDetail.create(wish))
    }
}
