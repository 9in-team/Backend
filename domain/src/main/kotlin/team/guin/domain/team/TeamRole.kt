package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class TeamRole(
    @Column(nullable = false, length = 30)
    var name: String,
    @Column(nullable = false)
    var required: Int,
    @Column(nullable = false)
    var hired: Int = 0,
) : BaseEntity() {
    companion object {
        fun create(name: String, roleRequired: Int): TeamRole {
            return TeamRole(name = name, required = roleRequired)
        }
    }
}
