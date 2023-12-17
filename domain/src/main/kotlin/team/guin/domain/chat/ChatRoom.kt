package team.guin.domain.chat

import org.hibernate.annotations.Comment
import team.guin.domain.account.Account
import team.guin.domain.baseentity.BaseEntity
import team.guin.domain.team.Team
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class ChatRoom(
    @Comment("처음 메시지를 보낸 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    val guest: Account?,
    @Comment("채팅방 초대 받은 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    val host: Account?,
    @Comment("채팅주제에 해당되는 팀")
    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team,
    @Comment("채팅 메시지 내용")
    @OneToMany
    @JoinColumn(name = "chat_room_id")
    val chatMessages: MutableList<Chat> // limit 1 -> length 1
) : BaseEntity() {
    // find => 찾을 때 => 있을 수도 있고 없을 수도 있다. => DB에서 꺼내올 때
    // get => 있다 => 해당 필드가 존재하지 않으면 에러 => 필드를 조회할 때 or 계산된 필드를 조회할 때
    fun findNickname(loginAccount: Account): String {
        return if (loginAccount != guest) {
            guest?.nickname ?: WITHDRAW_NICKNAME
        } else {
            host?.nickname ?: WITHDRAW_NICKNAME
        }
    }

    companion object {
        private val WITHDRAW_NICKNAME = "탈퇴 회원"
    }
}
