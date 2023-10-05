package team.guin.domain.chat.dto

import team.guin.domain.chat.Chat
import java.time.LocalDateTime

data class ChatMessageDetail(
    val sendTime: LocalDateTime,
    val message: String
) {
    companion object {
        fun findLastChatMessage(chats: List<Chat>): ChatMessageDetail {
            val lastChat: Chat = chats.sortedByDescending { it.createdAt }.get(0)
            return ChatMessageDetail(
                sendTime = lastChat.createdAt,
                message = lastChat.message
            )
        }
    }
}

