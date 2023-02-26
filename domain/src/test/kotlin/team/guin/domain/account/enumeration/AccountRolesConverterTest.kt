package team.guin.domain.account.enumeration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.instanceOf

class AccountRolesConverterTest : FreeSpec({
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

        userDatabaseColumn?.role shouldBe "USER"
        adminDatabaseCloumn?.role shouldBe "ADMIN"
    }
})
