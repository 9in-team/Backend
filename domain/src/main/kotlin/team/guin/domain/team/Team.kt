package team.guin.domain.team

import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Team(
    @ManyToOne(fetch = FetchType.LAZY)
    var account: Account,
    @Column(nullable = false)
    var subject: String,
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false, length = 500)
    var openChatUrl: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "team_id")
    var templates: MutableList<TeamTemplate>,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "team_id")
    var roles: MutableList<TeamRole>,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "team_id")
    var hashTags: MutableList<HashTag>,
) : BaseEntity() {
    companion object {
        fun create(
            account: Account,
            subject: String,
            content: String,
            openChatUrl: String,
            templates: List<TeamTemplate>,
            roles: List<TeamRole>,
            hashTags: MutableList<HashTag>,
        ): Team {
            return Team(
                account = account,
                subject = subject,
                content = content,
                openChatUrl = openChatUrl,
                templates = templates.toMutableList(),
                roles = roles.toMutableList(),
                hashTags = hashTags,
            )
        }
    }
}
