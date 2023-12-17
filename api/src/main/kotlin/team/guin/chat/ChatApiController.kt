package team.guin.chat

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.account.AccountApiService
import team.guin.chat.dto.ChatRoomDetail
import team.guin.common.CommonResponse
import team.guin.config.annotation.AccountSession
import team.guin.domain.chat.ChatRoom
import team.guin.login.dto.AccountProfile

@RestController
@RequestMapping("/chat-room")
class ChatApiController(
    private val chatReadApiService: ChatReadApiService,
    private val accountApiService: AccountApiService
) {

    @GetMapping("/list")
    fun findAllChatRoom(@AccountSession account: AccountProfile): CommonResponse<List<ChatRoomDetail>>? {
        val loginAccount = accountApiService.findById(account.id)
        val chatRooms: List<ChatRoom> = chatReadApiService.findAllChatRoom(loginAccount.id)
        return CommonResponse.okWithDetail(ChatRoomDetail.toChatRoomListDetail(chatRooms, loginAccount))
    }
}
