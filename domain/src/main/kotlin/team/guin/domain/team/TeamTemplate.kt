package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.TemplateType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class TeamTemplate(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    var type: TemplateType,
    @Column(nullable = false, length = 900)
    var question: String,
    var options: String?,
) : BaseEntity()
