package team.guin.domain

import team.guin.domain.enumeration.account.AccountRole
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0,
    @Column(nullable = false, length = 30)
    var email: String,
    @Column(nullable = false, length = 15)
    var nickname: String,
    @Column
    var imageId: String,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var accountRole: AccountRole,
) {
    constructor() : this(0, "", "", "", AccountRole.USER)

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
