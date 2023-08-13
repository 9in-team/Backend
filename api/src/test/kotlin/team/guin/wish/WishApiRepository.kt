package team.guin.wish

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WishApiRepository : JpaRepository<Wish, Long>
