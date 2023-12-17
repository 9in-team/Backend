package team.guin.chat

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.domain.chat.ChatRoom

@Service
@Transactional(readOnly = true)
class ChatReadApiService(
    private val chatRoomApiQueryDSLRepository: ChatRoomApiQueryDSLRepository
) {
    fun findAllChatRoom(accountId: Long): List<ChatRoom> {
        return chatRoomApiQueryDSLRepository.findAllChatRoom(accountId)
    }
}
