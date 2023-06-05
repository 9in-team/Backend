package team.guin.util

import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account

fun AccountApiRepository.createAccount(
    email: String = "email",
    nickname: String = "nickname",
    imageId: String = "imageId",
): Account = this.saveAndFlush(
    Account.create(
        email = email,
        nickname = nickname,
        imageId = imageId,
    ),
)
