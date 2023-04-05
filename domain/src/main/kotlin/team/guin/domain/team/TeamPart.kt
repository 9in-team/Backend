package team.guin.domain.team

import org.hibernate.annotations.Comment
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class TeamPart(
    @Comment("역할 이름")
    @Column(nullable = false, length = 30)
    var name: String,
    @Comment("필요한 팀원 수")
    @Column(nullable = false)
    var requiredCount: Int,
    @Comment("고용된 팀원 수")
    @Column(nullable = false)
    var hiredCount: Int,
) : BaseEntity() {
    companion object {
        fun create(name: String, roleRequired: Int): TeamPart {
            return TeamPart(name = name, requiredCount = roleRequired, hiredCount = 0)
        }
    }
}
