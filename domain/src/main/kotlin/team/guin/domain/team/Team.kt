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
    var subject: String,
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false, length = 500)
    var openChatUrl: String,
    @OneToMany(mappedBy = "team", orphanRemoval = true, cascade = [CascadeType.ALL])
    var templates: MutableList<TeamTemplate>,
    @OneToMany(mappedBy = "team", orphanRemoval = true, cascade = [CascadeType.ALL])
    var roles: MutableList<TeamRole>,
    // @OneToMany(mappedBy = "team")
    // var hashTags: MutableList<TeamHashTag>,
) : BaseEntity() {
    companion object {
        fun create(
            account: Account,
            teamSubject: String,
            teamContent: String,
            teamOpenChatUrl: String,
            teamTemplates: List<TeamTemplate>,
            roles: List<TeamRole>,
            //  teamHashTag: List<TeamHashTag>,
        ): Team {
            return Team(
                account = account,
                subject = teamSubject,
                content = teamContent,
                openChatUrl = teamOpenChatUrl,
                templates = teamTemplates.toMutableList(),
                roles = roles.toMutableList(),
                // hashTags = teamHashTag.toMutableList(),
            )
        }
    }
}
