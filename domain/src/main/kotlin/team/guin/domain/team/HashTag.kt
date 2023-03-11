package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class HashTag(
    @Column(nullable = false)
    val hashTagName: String,
    @Column(nullable = false)
    val hashTagIsStudy: Boolean,
) : BaseEntity()
