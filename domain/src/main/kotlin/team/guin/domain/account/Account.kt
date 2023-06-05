package team.guin.domain.account

import org.hibernate.annotations.Where
import team.guin.domain.account.enumeration.AccountRole
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@Where(clause = "deleted_at IS NULL")
class Account(
    @Column(nullable = false, length = 30, unique = true)
    var email: String,
    @Column(nullable = false, length = 15, unique = true)
    var nickname: String,
    @Column(length = 1000)
    var imageUrl: String,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var accountRole: AccountRole,
) : BaseEntity() {
    fun updateInfo(nickname: String, imageId: String) {
        this.nickname = nickname
        this.imageUrl = imageId
    }

    companion object {
        fun create(email: String, nickname: String, imageId: String): Account {
            return Account(
                email = email,
                nickname = nickname,
                imageUrl = imageId,
                accountRole = AccountRole.USER,
            )
        }
    }
}
