package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class TemplateOption(
    @ManyToOne(fetch = FetchType.LAZY)
    var teamTemplate: TeamTemplate,
    @Column(nullable = false, length = 150)
    var optionName: String,
) : BaseEntity()
