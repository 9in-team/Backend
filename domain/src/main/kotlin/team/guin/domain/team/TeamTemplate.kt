package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.TemplateType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TeamTemplate(
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var templateType: TemplateType,
    @Column(nullable = false)
    var templateQuestion: String,
) : BaseEntity()
