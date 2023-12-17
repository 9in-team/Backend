package team.guin.chat.dto

import team.guin.domain.chat.dto.ChatMessageSend

/**
 * packageName    : team.guin.chat.dto
 * fileName       : SendMessage
 * author         : jhw1015
 * date           : 2023/10/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/03           jhw1015           최초 생성
 */
data class SendMessage(val content: String, val senderId: Long, val receiverId: Long, val roomId: Long?, val teamId: Long) {
    fun create(): ChatMessageSend {
        return ChatMessageSend(
            content = content,
            receiverId = receiverId,
            senderId = senderId,
            roomId = roomId,
            teamId = teamId
        )
    }
}
