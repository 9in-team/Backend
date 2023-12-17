package team.guin.chat

import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.guin.domain.chat.ChatRoom
import team.guin.domain.chat.QChat
import team.guin.domain.chat.QChat.chat
import team.guin.domain.chat.QChatRoom.chatRoom
import java.beans.Expression

@Repository
class ChatRoomApiQueryDSLRepository(
    private val queryFactory: JPAQueryFactory
) {

    /**
     * todo
     * chatMessage 하위쿼리로 1개만 데이터 가져오기
     * select문 수정
     * */
    fun findAllChatRoom(loginAccountId: Long): List<ChatRoom> {
        return queryFactory.select(
            Projections.constructor(ChatRoom::class.java ,chatRoom.guest, chatRoom.host, chatRoom.team,
                JPAExpressions.select()
                    .from(chat)
                    .where(chat.`in`(chatRoom.chatMessages))
                    .orderBy(chat.createdAt.desc())
                    .limit(0)
                ))
            .from(chatRoom)
            .leftJoin(chatRoom.guest)
            .leftJoin(chatRoom.host)
            .where(chatRoom.guest.id.eq(loginAccountId).or(chatRoom.host.id.eq(loginAccountId)))
            .orderBy(chatRoom.createdAt.desc())
            .fetch()

    }
}
