package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
class HashTag(
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var subjectType: SubjectType,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var type: TagType,
    @OneToMany
    @JoinColumn(name = "hash_tag_id")
    var teamHashTag: MutableList<TeamHashTag>? = null,
) : BaseEntity()
