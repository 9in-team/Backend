package team.guin.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import team.guin.chat.ChatWriteService
import team.guin.chat.dto.SendMessage

@Component
class SocketHandler(
    private val mapper: ObjectMapper,
    private val socketSessions: HashSet<WebSocketSession> = HashSet(),
    private val chatWriteService: ChatWriteService
) : TextWebSocketHandler() {
    private val chatRoomSessionMap: HashMap<Long, Set<WebSocketSession>> = HashMap()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("연결")
        socketSessions.add(session)
        for (socketSession in socketSessions) {
            println(socketSession)
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("연결 끊김")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val sendMessage = mapper.readValue(message.payload, SendMessage::class.java)
        chatWriteService.create(sendMessage.create())
    }
}