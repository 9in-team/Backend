package team.guin.util

import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.team.HashTag
import team.guin.domain.team.Team
import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import team.guin.domain.team.enumeration.TemplateType
import team.guin.team.TeamApiRepository

fun AccountApiRepository.createAccount(
    email: String = "email",
    nickname: String = "nickname",
    imageId: String = "imageId",
): Account = this.saveAndFlush(
    Account.create(
        email = email,
        nickname = nickname,
        imageUrl = imageId,
    ),
)

fun TeamApiRepository.createTeam(
    leader: Account,
    subject: String = "스터디 구합니다.",
    content: String = "내용",
    openChatUrl: String = "http://9in-team.co.kr",
    subjectType: SubjectType,
): Team = this.saveAndFlush(
    Team.create(
        leader = leader,
        subject = subject,
        content = content,
        openChatUrl = openChatUrl,
        templates = listOf(
            TeamTemplate(type = TemplateType.CHECKBOX, question = "열심히1", options = null),
            TeamTemplate(type = TemplateType.CHECKBOX, question = "열심히2", options = null),
        ),
        roles = listOf(TeamRole(name = "프론트엔드", requiredCount = 3, hiredCount = 0)),
        subjectType = subjectType,
        hashTags = listOf(HashTag(TagType.JAVA), HashTag(TagType.KOTLIN)),
    ),
)
