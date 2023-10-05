package team.guin.chat.dto

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
data class SendMessage(val content: String, val senderId: Long, val roomId: Long)
