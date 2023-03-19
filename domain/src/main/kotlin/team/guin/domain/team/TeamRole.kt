package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TeamRole(
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,
    @Column(nullable = false, length = 30)
    var roleName: String,
    @Column(nullable = false)
    var roleRequired: Int,
    @Column(nullable = false)
    var roleHired: Int = 0,
) : BaseEntity() {
    companion object {
        fun create(team: Team, roleName: String, roleRequired: Int): TeamRole {
            return TeamRole(
                team = team,
                roleName = roleName,
                roleRequired = roleRequired,
            )
        }
    }
}
