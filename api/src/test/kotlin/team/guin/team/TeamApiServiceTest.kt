package team.guin.team

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.guin.account.AccountApiRepository
import team.guin.domain.team.HashTag
import team.guin.domain.team.TeamPart
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.TemplateOption
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import team.guin.domain.team.enumeration.TemplateType
import team.guin.util.createAccount

@SpringBootTest
class TeamApiServiceTest(
    private val accountApiRepository: AccountApiRepository,
    private val teamApiService: TeamApiService,
    private val teamApiRepository: TeamApiRepository,
) : FreeSpec({
    "create" - {
        "템플릿, 해시태그, 역할, 주제, 오픈채팅 주소를 작성하면 팀이 생성된다." - {
            // given
            val account = accountApiRepository.createAccount()
            val templates: List<TeamTemplate> = listOf(
                TeamTemplate(TemplateType.TEXT, "test", mutableListOf()),
                TeamTemplate(
                    TemplateType.RADIOBOX,
                    "test2",
                    mutableListOf(TemplateOption.create("Yes"), TemplateOption.create("No")),
                ),
            )
            val subject = "9in"
            val subjectType = SubjectType.PROJECT
            val content = "9in 프로젝트 구해요"
            val openChatUrl = "http://9in-proejct.chat"
            val parts = listOf(TeamPart.create("백엔드", 2))
            val hashTags =
                listOf(
                    HashTag(subjectType, TagType.JAVA),
                    HashTag(subjectType, TagType.MYSQL),
                )
            val teamCreate = TeamCreate(
                subject = subject,
                content = content,
                openChatUrl = openChatUrl,
                templates = templates,
                hashTags = hashTags,
                parts = parts,
            )

            // when
            val team = teamApiService.createTeam(account.id, teamCreate)

            // then
            team.content shouldBe content
            team.openChatUrl shouldBe openChatUrl
            team.hashTags.size shouldBe teamCreate.hashTags.size
            team.templates.size shouldBe teamCreate.templates.size
            team.parts.size shouldBe teamCreate.parts.size
        }
        "모집글이 삭제되면 템플릿, 역할, 해시태그가 같이 삭제된다." - {
            // given
            val account = accountApiRepository.createAccount()
            val templates: List<TeamTemplate> = listOf(
                TeamTemplate(TemplateType.TEXT, "test", mutableListOf()),
                TeamTemplate(
                    TemplateType.RADIOBOX,
                    "test2",
                    mutableListOf(TemplateOption.create("Yes"), TemplateOption.create("No")),
                ),
            )
            val subject = "9in"
            val subjectType = SubjectType.PROJECT
            val content = "9in 프로젝트 구해요"
            val openChatUrl = "http://9in-proejct.chat"
            val parts = listOf(TeamPart.create("백엔드", 2))
            val hashTags =
                listOf(
                    HashTag(subjectType, TagType.JAVA),
                    HashTag(subjectType, TagType.MYSQL),
                )
            val teamCreate = TeamCreate(
                subject = subject,
                content = content,
                openChatUrl = openChatUrl,
                templates = templates,
                hashTags = hashTags,
                parts = parts,
            )

            val team = teamApiService.createTeam(account.id, teamCreate)
            val saveTeamId = team.id

            // when
            teamApiRepository.delete(team)

            // then
            val findTeam = teamApiRepository.findByIdOrNull(saveTeamId)
            findTeam shouldBe null
        }
    }
})
