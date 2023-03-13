package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TeamHashTag(
    @ManyToOne(fetch = FetchType.LAZY)
    var hashTag: HashTag,
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,
) : BaseEntity()
