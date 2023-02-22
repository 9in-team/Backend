package team.guin.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.Account

@Repository
interface AccountApiRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?
}
