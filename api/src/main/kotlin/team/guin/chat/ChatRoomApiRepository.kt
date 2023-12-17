package team.guin.chat

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import team.guin.domain.chat.ChatRoom

interface ChatRoomApiRepository : JpaRepository<ChatRoom, Long> {

    @Query(value = "SELECT cr FROM ChatRoom cr " +
        "WHERE cr.guest.id = :accountId OR cr.host.id = :accountId")
    fun findAllChatRoomByAccountId(accountId: Long) : List<ChatRoom>
}
