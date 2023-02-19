package team.guin.domain.enumeration

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import team.guin.domain.enumeration.account.AccountRoles
import java.lang.IllegalArgumentException

class AccountRolesTest : FreeSpec({
    "AccountRoles" - {
        "AccountRoles를 조회를 성공한다." {
            // given
            // when
            val userRole = AccountRoles.fromCode("USER")
            val adminRole = AccountRoles.fromCode("ADMIN")
            // then
            userRole shouldBe instanceOf(AccountRoles::class)
            adminRole shouldBe instanceOf(AccountRoles::class)
            userRole.role shouldBe "USER"
            adminRole.role shouldBe "ADMIN"
        }
    }
    "Exception" - {
        "AccountRoles를 조회를 실패한다." {

            // given/when/then
            shouldThrowExactly<IllegalArgumentException> { AccountRoles.fromCode("TEST") }
        }
    }
})
