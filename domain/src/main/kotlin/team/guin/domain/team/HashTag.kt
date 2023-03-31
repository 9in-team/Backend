package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(indexes = [Index(name = "hash_tag_idx", columnList = "type")])
class HashTag(
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var subjectType: SubjectType,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var type: TagType, // index
) : BaseEntity() {
    companion object {
        fun create(subjectType: SubjectType, type: TagType): HashTag {
            return HashTag(
                subjectType = subjectType,
                type = type,
            )
        }
    }
}
