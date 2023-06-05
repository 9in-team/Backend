package team.guin.team

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.team.Team

@Repository
interface TeamApiRepository : JpaRepository<Team, Long>
