package team.guin.chat.dto

import com.fasterxml.jackson.annotation.JsonFormat
import team.guin.domain.account.Account
import team.guin.domain.chat.ChatRoom
import java.time.LocalDateTime

data class ChatRoomDetail(
    val otherName: String,
    @JsonFormat(pattern = "yyyy년 MM월 dd일 hh시 mm분 ss분")
    val sendTime: LocalDateTime,
    val message: String,
    val unReadCnt: Int
) {
    // chatroom -> message
    companion object {
        fun toChatRoomListDetail(chatRooms: List<ChatRoom>, loginAccount: Account): List<ChatRoomDetail> {
            return chatRooms.map {
                ChatRoomDetail(
                    otherName = it.findNickname(loginAccount),
                    sendTime = it.chatMessages[0].createdAt,
                    message = it.chatMessages[0].message,
                    unReadCnt = it.chatMessages.count { chatMessage -> chatMessage.sender != loginAccount && !chatMessage.isRead }
                )
            }
        }
    }
}
