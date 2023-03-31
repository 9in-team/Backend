package team.guin.account

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.account.dto.AccountDetail
import team.guin.account.dto.AccountJoinRequest
import team.guin.account.dto.AccountUpdateRequest
import team.guin.common.CommonResponse
import team.guin.security.kakao.AccountProfile
import team.guin.security.kakao.KakaoApiService
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/account")
class AccountApiController(
    private val accountApiService: AccountApiService,
    private val kakaoApiService: KakaoApiService,
    private val accountApiRepository: AccountApiRepository,
    private val springSessionDefaultRedisSerializer: GenericJackson2JsonRedisSerializer,
) {
    @PostMapping
    fun join(
        @RequestBody accountJoinRequest: AccountJoinRequest,
        httpSession: HttpSession,
    ): CommonResponse<AccountProfile> {
        val accessToken = accountJoinRequest.accessToken
        val kakaoDetailProfile = kakaoApiService.fetchKakaoDetailProfile(accessToken)
        val account = accountApiRepository.findByEmail(kakaoDetailProfile.email)
            ?: accountApiRepository.save(kakaoDetailProfile.toEntity())
        val accountProfile = AccountProfile.from(account)
        val serialize = springSessionDefaultRedisSerializer.serialize(accountProfile)
        httpSession.setAttribute("USER", accountProfile)
        return CommonResponse.okWithDetail(accountProfile)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody accountUpdateRequest: AccountUpdateRequest): CommonResponse<AccountDetail> {
        val account = accountApiService.updateInfo(id, accountUpdateRequest.nickname, accountUpdateRequest.imageId)
        return CommonResponse.okWithDetail(AccountDetail(account.email, account.nickname, account.imageId))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): CommonResponse<Unit> {
        accountApiService.delete(id)
        return CommonResponse.ok()
    }
}
