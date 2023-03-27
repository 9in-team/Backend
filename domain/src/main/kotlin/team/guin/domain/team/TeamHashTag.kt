package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TeamHashTag(
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,
    @ManyToOne(fetch = FetchType.LAZY)
    var hashTag: HashTag,

) : BaseEntity() {

    companion object {
        fun create(team: Team, hashTag: HashTag): TeamHashTag {
            return TeamHashTag(
                team = team,
                hashTag = hashTag,
            )
        }
    }
}
