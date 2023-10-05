package team.guin.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

/**
 * packageName    : team.guin.config
 * fileName       : SocketHanlder
 * author         : jhw1015
 * date           : 2023/10/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/02           jhw1015           최초 생성
 */
@Component
class SocketHandler(
    private val mapper: ObjectMapper,
    private val socketSessions: HashSet<WebSocketSession> = HashSet()
) : TextWebSocketHandler() {
    private var chatRoomSessionMap: HashMap<Long,Set<WebSocketSession>> = HashMap()
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

    }

}
