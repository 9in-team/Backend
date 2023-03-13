package team.guin.domain.team

import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Team(
    @ManyToOne(fetch = FetchType.LAZY)
    var account: Account,
    @Column(nullable = false)
    var teamSubject: String,
    @Column(nullable = false)
    var teamContent: String,
    @Column(nullable = false, length = 500)
    var teamOpenChatUrl: String,
) : BaseEntity()
