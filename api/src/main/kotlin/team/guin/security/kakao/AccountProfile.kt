package team.guin.security.kakao

import team.guin.domain.account.Account
import kotlin.properties.Delegates

data class AccountProfile(
    val email: String,
    val nickname: String,
    val imageUrl: String,
) {
    var id by Delegates.notNull<Long>()

    companion object {
        fun from(kakaoDetailProfile: KakaoDetailProfile): AccountProfile {
            return AccountProfile(
                kakaoDetailProfile.email,
                kakaoDetailProfile.nickname,
                kakaoDetailProfile.imageUrl,
            )
        }

        fun from(account: Account): AccountProfile {
            val accountProfile = AccountProfile(
                account.email,
                account.nickname,
                account.imageId,
            )
            accountProfile.id = account.id

            return accountProfile
        }
    }
}
