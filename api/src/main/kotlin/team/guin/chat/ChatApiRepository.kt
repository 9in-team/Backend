package team.guin.chat

import org.springframework.data.jpa.repository.JpaRepository
import team.guin.domain.chat.Chat

interface ChatApiRepository : JpaRepository<Chat, Long>
