package team.guin.team

import org.springframework.stereotype.Repository
import team.guin.domain.baseentity.BaseRepository
import team.guin.domain.team.HashTag
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType

@Repository
interface HashTagApiRepository : BaseRepository<HashTag, Long> {
    fun findByTypeAndSubjectType(tagType: TagType, type: SubjectType): HashTag?
}
