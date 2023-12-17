package team.guin.domain.chat.dto

data class ChatMessageSend(
    val senderId: Long,
    val receiverId: Long,
    val roomId: Long?,
    val content: String,
    val teamId: Long
)

