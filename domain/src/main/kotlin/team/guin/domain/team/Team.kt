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
    var leader: Account,
    @Column(nullable = false)
    var subject: String,
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false, length = 500)
    var openChatUrl: String,
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var templates: MutableList<TeamTemplate>,
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var parts: MutableList<TeamPart>,
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var hashTags: MutableList<HashTag>,
) : BaseEntity() {
    companion object {
        fun create(
            leader: Account,
            subject: String,
            content: String,
            openChatUrl: String,
            templates: List<TeamTemplate>,
            parts: List<TeamPart>,
            hashTags: List<HashTag>,
        ): Team {
            return Team(
                leader = leader,
                subject = subject,
                content = content,
                openChatUrl = openChatUrl,
                templates = templates.toMutableList(),
                parts = parts.toMutableList(),
                hashTags = hashTags.toMutableList(),
            )
        }
    }
}
