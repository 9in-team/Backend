package team.guin.team

import org.springframework.stereotype.Repository
import team.guin.domain.baseentity.BaseRepository
import team.guin.domain.team.TeamTemplate

@Repository
interface TeamTemplateApiRepository : BaseRepository<TeamTemplate, Long>
