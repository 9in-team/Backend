package team.guin.domain.chat

import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Chat(
    @Comment("채팅 보낸 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    val sender: Account,
    @Comment("채팅 받는 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    val receiver: Account,
    @Comment("메시지")
    @Column(columnDefinition = "TEXT", nullable = false)
    val message: String,
    @Comment("채팅 읽었는지에 대한 여부")
    @ColumnDefault("false")
    val isRead: Boolean = false
) : BaseEntity()

