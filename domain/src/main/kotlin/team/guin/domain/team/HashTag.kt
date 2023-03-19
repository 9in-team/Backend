package team.guin.domain.team

import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.HashTagType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class HashTag(
    @Column(nullable = false, length = 20)
    var hashTagName: String,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var hashtagType: HashTagType,
) : BaseEntity() {
    companion object {
        fun create(hashTagName: String, hashTagType: HashTagType): HashTag {
            return HashTag(hashTagName, hashTagType)
        }
    }
}
