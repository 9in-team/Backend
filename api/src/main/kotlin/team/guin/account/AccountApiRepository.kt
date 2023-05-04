package team.guin.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.account.Account

@Repository
interface AccountApiRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?
}
