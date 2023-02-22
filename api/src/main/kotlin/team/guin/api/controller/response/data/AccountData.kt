package team.guin.api.controller.response.data

import team.guin.domain.Account

data class AccountData(val email: String, val nickname: String, val imageId: String) {
    companion object {
        fun fromEntity(entity: Account): AccountData {
            return AccountData(entity.email, entity.nickname, entity.imageId)
        }
    }
}
