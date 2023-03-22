package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TeamRole(
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team? = null,
    @Column(nullable = false, length = 30)
    var name: String,
    @Column(nullable = false)
    var required: Int,
    @Column(nullable = false)
    var hired: Int = 0,
) : BaseEntity()
