package team.guin.domain.team

import org.hibernate.annotations.Comment
import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.TemplateType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class TeamTemplate(
    @Comment("템플릿 타입")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    var type: TemplateType,
    @Comment("질문")
    @Column(nullable = false, length = 900)
    var question: String,
    @Comment("옵션")
    var options: String?,
) : BaseEntity()
