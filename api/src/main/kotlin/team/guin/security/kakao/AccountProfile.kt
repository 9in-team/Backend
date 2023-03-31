package team.guin.security.kakao

import team.guin.domain.account.Account

data class AccountProfile(
    val id: Long,
    val email: String,
    val nickname: String,
    val imageUrl: String,
) {
    companion object {
        fun from(account: Account): AccountProfile {
            val accountProfile = AccountProfile(
                id = account.id,
                email = account.email,
                nickname = account.nickname,
                imageUrl = account.imageId,
            )

            return accountProfile
        }
    }
}
