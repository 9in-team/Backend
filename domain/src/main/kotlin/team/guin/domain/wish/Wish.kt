package team.guin.domain.wish

import org.springframework.util.Assert
import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.Team
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "wish")
class Wish(
    @ManyToOne(fetch = FetchType.LAZY)
    val account: Account,
    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team,
) : BaseEntity() {

    companion object {
        fun create(user: Account?, team: Team): Wish {
            Assert.notNull(team.id, "팀이 존재하지 않습니다.")
            Assert.notNull(user!!.id, "유저가 존재하지 않습니다.")
            return Wish(user, team)
        }
    }
}
