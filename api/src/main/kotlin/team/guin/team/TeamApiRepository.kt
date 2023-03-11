package team.guin.team

import org.springframework.stereotype.Repository
import team.guin.domain.baseentity.BaseRepository
import team.guin.domain.team.Team

@Repository
interface TeamApiRepository : BaseRepository<Team, Long>
