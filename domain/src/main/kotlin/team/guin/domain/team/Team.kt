package team.guin.domain.team

import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

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
    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var teamTemplates: MutableList<TeamTemplate>? = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun create(account: Account, teamSubject: String, teamContent: String, teamOpenChatUrl: String): Team {
            return Team(
                account = account,
                teamSubject = teamSubject,
                teamContent = teamContent,
                teamOpenChatUrl = teamOpenChatUrl,
            )
        }
    }
}
