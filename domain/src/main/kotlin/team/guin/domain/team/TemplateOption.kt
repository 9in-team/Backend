package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class TemplateOption(
    @Column(nullable = false, length = 150)
    var name: String, // ENUM
) : BaseEntity() {
    companion object {
        fun create(
            name: String,
        ): TemplateOption {
            return TemplateOption(name)
        }
    }
}
