package team.guin.team

import org.springframework.stereotype.Repository
import team.guin.domain.baseentity.BaseRepository
import team.guin.domain.team.TemplateOption

@Repository
interface TemplateOptionApiRepository : BaseRepository<TemplateOption, Long>
