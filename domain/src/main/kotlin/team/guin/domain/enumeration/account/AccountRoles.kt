package team.guin.domain.enumeration.account

import java.util.*

enum class AccountRoles(val role: String) {
    ADMIN("ADMIN"),
    USER("USER"),
    ;

    companion object {
        fun fromCode(dbData: String): AccountRoles {
            return Arrays.stream(AccountRoles.values())
                .filter { v -> v.role == dbData }
                .findAny()
                .orElseThrow { IllegalArgumentException("존재하지 않은 권한입니다.") }
        }
    }
}
