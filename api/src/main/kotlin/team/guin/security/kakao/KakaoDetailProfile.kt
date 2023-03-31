package team.guin.security.kakao

import team.guin.domain.account.Account

data class KakaoDetailProfile(
    private val id: Long,
    private val properties: Properties?,
    private val kakao_account: KakaoAccount,
) {
    fun toEntity(): Account {
        return Account.create(email = this.email, nickname = this.nickname, imageId = this.imageUrl)
    }

    val email = kakao_account.email

    fun toEntity(): Account {
        return Account.create(email = this.email, nickname = kakao_account.profile.nickname, imageId = kakao_account.profile.profile_image_url)
    }
}

data class KakaoAccount(
    private val profile_needs_agreement: Boolean?,
    val profile: Profile,
    private val email_needs_agreement: Boolean?,
    private val is_email_valid: Boolean?,
    private val is_email_verified: Boolean?,
    val email: String,
    private val name_needs_agreement: Boolean?,
    private val name: String?,
    private val age_range_needs_agreement: Boolean?,
    private val age_range: String?,
    private val birthday_needs_agreement: Boolean?,
    private val birthday: String?,
    private val gender_needs_agreement: Boolean?,
    private val gender: String?,
)

data class Profile(
    val nickname: String,
    private val thumbnail_image_url: String?,
    val profile_image_url: String,
    private val is_default_image: Boolean?,
)

data class Properties(
    private val nickname: String?,
    private val thumbnail_image: String?,
    private val profile_image: String?,
    private val custom_field_1: String?,
    private val custom_field_2: String?,
)
