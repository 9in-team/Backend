package team.guin.domain.team

import org.hibernate.annotations.Comment
import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class HashTag(
    @Comment("팀 주제 타입")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var subjectType: SubjectType,
    @Comment("팀 해시태그")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var type: TagType,
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
