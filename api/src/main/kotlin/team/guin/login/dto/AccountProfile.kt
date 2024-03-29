package team.guin.login.dto

import team.guin.domain.account.Account

data class AccountProfile(
    val id: Long,
    val email: String,
    val nickname: String,
    val imageUrl: String,
) {
    constructor() : this(0L, "", "", "")

    companion object {
        fun from(account: Account): AccountProfile {
            return AccountProfile(
                id = account.id,
                email = account.email,
                nickname = account.nickname,
                imageUrl = account.imageUrl,
            )
        }
    }
}
