package team.guin.wish

import team.guin.domain.wish.Wish

class WishCreateDetail(
    val wishId: Long,
    val teamId: Long,
    val accountId: Long,
) {
    companion object {
        fun create(wish: Wish): WishCreateDetail {
            return WishCreateDetail(
                wishId = wish.id,
                teamId = wish.team.id,
                accountId = wish.account.id,
            )
        }
    }
}
