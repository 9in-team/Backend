package team.guin.domain.account

import org.hibernate.annotations.Where
import team.guin.domain.account.enumeration.AccountRole
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "account")
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
        private const val DEFAULT_IMAGE_URL = "https://i.imgur.com/WxNkK7J.png"

        fun create(email: String, nickname: String, imageUrl: String = DEFAULT_IMAGE_URL): Account {
            return Account(
                email = email,
                nickname = nickname,
                imageUrl = imageUrl,
                accountRole = AccountRole.USER,
            )
        }
    }
}
