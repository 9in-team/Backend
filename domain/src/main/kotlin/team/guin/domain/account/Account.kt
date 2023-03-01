package team.guin.domain.account

import team.guin.domain.account.enumeration.AccountRoles
import team.guin.domain.account.enumeration.AccountRolesConverter
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity

@Entity
class Account(
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
) : BaseEntity() {

    fun updateInfo(nickname: String, imageId: String) {
        this.nickname = nickname
        this.imageId = imageId
    }

    companion object {
        fun create(email: String, nickname: String, imageId: String, accountRoles: AccountRoles = AccountRoles.USER): Account {
            return Account(email, nickname, imageId, accountRoles)
        }
    }
}
