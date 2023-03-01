package team.guin.account

import org.springframework.stereotype.Repository
import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseRepository

@Repository
interface AccountApiRepository : BaseRepository<Account, Long> {
    fun findByEmail(email: String): Account?
}
