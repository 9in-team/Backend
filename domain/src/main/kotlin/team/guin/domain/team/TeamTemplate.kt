package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.TemplateType
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
class TeamTemplate(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    var type: TemplateType,
    @Column(nullable = false, length = 900)
    var question: String,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "team_template_id")
    var options: MutableList<TemplateOption> = mutableListOf(),
) : BaseEntity()
