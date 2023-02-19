package team.guin.api.enueration.account

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.instanceOf
import team.guin.domain.enumeration.account.AccountRoles
import team.guin.domain.enumeration.account.AccountRolesConverter

class AccountRolesConverterTest() : FreeSpec({
    "AccountRolesConverter" - {
        "convertToDatabaseColumn" {
            // given
            val accountRolesConverter = AccountRolesConverter()
            val user = AccountRoles.USER
            val admin = AccountRoles.ADMIN
            // when
            val userDatabaseColumn = accountRolesConverter.convertToDatabaseColumn(user)
            val adminDatabaseCloumn = accountRolesConverter.convertToDatabaseColumn(admin)
            // then

            userDatabaseColumn shouldBe "USER"
            adminDatabaseCloumn shouldBe "ADMIN"
        }
    }
    "convertToEntityAttribute" {
        // given
        val accountRolesConverter = AccountRolesConverter()
        val user = "USER"
        val admin = "ADMIN"
        // when
        val userDatabaseColumn = accountRolesConverter.convertToEntityAttribute(user)
        val adminDatabaseCloumn = accountRolesConverter.convertToEntityAttribute(admin)
        // then
        userDatabaseColumn shouldBe instanceOf(AccountRoles::class)
        adminDatabaseCloumn shouldBe instanceOf(AccountRoles::class)
        userDatabaseColumn shouldNotBe null
        adminDatabaseCloumn shouldNotBe null
        if (userDatabaseColumn != null) {
            userDatabaseColumn.role shouldBe "USER"
        }
        if (adminDatabaseCloumn != null) {
            adminDatabaseCloumn.role shouldBe "ADMIN"
        }
    }
})
