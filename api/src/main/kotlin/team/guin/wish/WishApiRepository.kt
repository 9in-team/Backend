package team.guin.wish

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.wish.Wish

@Repository
interface WishApiRepository : JpaRepository<Wish, Long> {
    fun deleteByIdAndAccountId(wishId: Long, accountId: Long): Int
}
