package team.guin.monolithic.application.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.guin.monolithic.domain.user.User
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserEmail(userEmail: String): Optional<User>
}