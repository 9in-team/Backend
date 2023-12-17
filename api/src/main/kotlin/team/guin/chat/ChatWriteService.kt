package team.guin.chat

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.guin.account.AccountApiRepository
import team.guin.domain.chat.Chat
import team.guin.domain.chat.dto.ChatMessageSend

@Service
@Transactional
class ChatWriteService(
    private val accountRepository: AccountApiRepository,
    private val chatApiRepository: ChatApiRepository,
    private val roomRepository: ChatRoomApiRepository
) {
    fun create(chatMessageSend: ChatMessageSend): Chat {
        val receiver = accountRepository.findByIdOrNull(chatMessageSend.receiverId) ?: throw IllegalArgumentException()
        val sender = accountRepository.findByIdOrNull(chatMessageSend.senderId) ?: throw IllegalArgumentException()
        val room = roomRepository.findByIdOrNull(chatMessageSend.roomId) ?: throw IllegalArgumentException()
        return chatApiRepository.save(Chat(sender = sender, receiver = receiver, message = chatMessageSend.content))
    }
}
