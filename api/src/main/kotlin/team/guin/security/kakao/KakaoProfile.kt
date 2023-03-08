package team.guin.security.kakao

data class KakaoProfile(
    private val id: Int,
    private val kakaoAccount: KakaoAccount,
    private val properties: Properties,
) {
    val email: String
        get() = kakaoAccount.email
    val imageUrl: String
        get() = kakaoAccount.profile.profileImageUrl
    val nickname: String
        get() = kakaoAccount.profile.nickname
}

data class KakaoAccount(
    private val profileNeedsAgreement: Boolean,
    val profile: Profile,
    private val emailNeedsAgreement: Boolean,
    private val isEmailValid: Boolean,
    private val isEmailVerified: Boolean,
    val email: String,
    private val nameNeedsAgreement: Boolean,
    private val name: String,
    private val ageRangeNeedsAgreement: Boolean,
    private val ageRange: String,
    private val birthdayNeedsAgreement: Boolean,
    private val birthday: String,
    private val genderNeedsAgreement: Boolean,
    private val gender: String,
)

data class Profile(
    val nickname: String,
    private val thumbnailImageUrl: String,
    val profileImageUrl: String,
    private val isDefaultImage: Boolean,
)

data class Properties(
    private val nickname: String,
    private val thumbnailImage: String,
    private val profileImage: String,
    private val customField1: String,
    private val customField2: String,
)
