package team.guin.wish

import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.account.AccountApiRepository
import team.guin.domain.wish.Wish
import team.guin.team.TeamApiRepository

@Service
class WishApiService(
    private val wishApiRepository: WishApiRepository,
    private val accountApiRepository: AccountApiRepository,
    private val teamApiRepository: TeamApiRepository,
) {
    fun create(accountId: Long, wishCreateRequest: WishCreateRequest): Wish {
        val account = accountApiRepository.findById(accountId)
            .orElseThrow { IllegalArgumentException("찾을 수 없는 사용자 입니다.") }
        val team = teamApiRepository.findById(wishCreateRequest.teamId)
            .orElseThrow { IllegalArgumentException("찾을 수 없는 팀 입니다.") }

        val wish = Wish.create(account, team)
        return wishApiRepository.save(wish)
    }

    @Transactional
    fun delete(accountId: Long, wishId: Long): Boolean {
        val wish = wishApiRepository.findById(wishId)
            .orElseThrow { IllegalArgumentException("찜하기[ID:$wishId]를 찾지 못하였습니다.") }

        if (wish.id != accountId) {
            throw AccessDeniedException("본인의 찜하기만 삭제가 가능합니다.")
        }
        val deleteByIdAndAccountId = wishApiRepository.deleteByIdAndAccountId(wishId, accountId)
        return deleteByIdAndAccountId > 0
    }
}
