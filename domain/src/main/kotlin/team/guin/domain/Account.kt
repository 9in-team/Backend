package team.guin.domain

import team.guin.domain.enumeration.account.AccountRoles
import team.guin.domain.enumeration.account.AccountRolesConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long,
    @Column
    var email: String,
    @Column
    var nickname: String,
    @Column
    var imageId: String,
    @Convert(
        converter = AccountRolesConverter::class,
    )
    val accountRoles: AccountRoles,
) {
    constructor() : this(0, "", "", "", AccountRoles.USER)
}
