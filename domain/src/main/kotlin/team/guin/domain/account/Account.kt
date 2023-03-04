package team.guin.domain.account

import team.guin.domain.account.enumeration.AccountRole
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Account(
    @Column(nullable = false, length = 30, unique = true)
    var email: String,
    @Column(nullable = false, length = 15, unique = true)
    var nickname: String,
    @Column(length = 1000)
    var imageId: String,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var accountRole: AccountRole,
) : BaseEntity() {
    fun updateInfo(nickname: String, imageId: String) {
        this.nickname = nickname
        this.imageId = imageId
    }

    companion object {
        fun create(email: String, nickname: String, imageId: String): Account {
            return Account(
                email = email,
                nickname = nickname,
                imageId = imageId,
                accountRole = AccountRole.USER,
            )
        }
    }
}
