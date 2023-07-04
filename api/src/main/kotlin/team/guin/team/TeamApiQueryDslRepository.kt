package team.guin.team

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.guin.domain.team.QTeam.team
import team.guin.domain.team.Team
import team.guin.domain.team.enumeration.SubjectType

@Repository
class TeamApiQueryDslRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun findAllBySubjectType(subjectType: SubjectType?): List<Team> {
        return jpaQueryFactory
            .selectFrom(team)
            .join(team.leader)
            .fetchJoin()
            .where(
                eqSubjectType(subjectType),
            ).fetch()
    }

    private fun eqSubjectType(subjectType: SubjectType?): BooleanExpression? {
        return subjectType?.let {
            team.subjectType.eq(it)
        }
    }
}
