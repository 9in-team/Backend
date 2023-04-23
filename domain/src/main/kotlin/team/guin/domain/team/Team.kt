package team.guin.domain.team

import org.hibernate.annotations.Comment
import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.enumeration.SubjectType
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Team(
    @Comment("팀장")
    @ManyToOne(fetch = FetchType.LAZY)
    var leader: Account,
    @Comment("주제")
    @Column(nullable = false)
    var subject: String,
    @Comment("팀 설명")
    @Column(nullable = false)
    var content: String,
    @Comment("오픈챗 URL")
    @Column(nullable = false, length = 500)
    var openChatUrl: String,
    @Comment("템플릿")
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var templates: MutableList<TeamTemplate>,
    @Comment("모집 역할")
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var roles: MutableList<TeamRole>,
    @Comment("해시태그")
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "team_id")
    var hashTags: MutableList<HashTag>,
    @Comment("팀 주제 타입")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var subjectType: SubjectType,
) : BaseEntity() {
    companion object {
        fun create(
            leader: Account,
            subject: String,
            content: String,
            openChatUrl: String,
            templates: List<TeamTemplate>,
            roles: List<TeamRole>,
            subjectType: SubjectType,
            hashTags: List<HashTag>,
        ): Team {
            return Team(
                leader = leader,
                subject = subject,
                content = content,
                openChatUrl = openChatUrl,
                templates = templates.toMutableList(),
                roles = roles.toMutableList(),
                subjectType = subjectType,
                hashTags = hashTags.toMutableList(),
            )
        }
    }
}
