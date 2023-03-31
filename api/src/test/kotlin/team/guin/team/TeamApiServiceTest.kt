package team.guin.team

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.team.HashTag
import team.guin.domain.team.TeamRole
import team.guin.domain.team.TeamTemplate
import team.guin.domain.team.TemplateOption
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.enumeration.SubjectType
import team.guin.domain.team.enumeration.TagType
import team.guin.domain.team.enumeration.TemplateType

@SpringBootTest
class TeamApiServiceTest(
    private val accountApiRepository: AccountApiRepository,
    private val teamApiService: TeamApiService,
    private val teamApiRepository: TeamApiRepository,
    private val templateOptionApiRepository: TemplateOptionApiRepository,
) : FreeSpec({
    "create" - {
        "템플릿, 해시태그, 역할, 주제, 오픈채팅 주소를 작성하면 팀이 생성된다." - {
            // given
            val account = accountApiRepository.save(Account.create("test@test.com", "nickname", "http://image.com"))
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
            val roles = listOf(TeamRole.create("백엔드", 2))
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
                roles = roles,
            )
            // when
            val team = teamApiService.createTeam(account.id, teamCreate)

            // then
            val findTeam = teamApiRepository.findById(team.id).get()
            val findTemplates = team.templates
            val findHashTags = team.hashTags
            val findTemplateOption = templateOptionApiRepository.findAll()

            findTeam.content shouldBe content
            findTeam.openChatUrl shouldBe openChatUrl
            findTeam.id shouldBe team.id
            findTemplates shouldNotBe null
            findTemplates.size shouldBe 2
            findHashTags.find { it.subjectType == subjectType } shouldNotBe null
            findHashTags.find { it.type == TagType.JAVA } shouldNotBe null
            findTemplateOption.find { it.name == "Yes" } shouldNotBe null
        }
        "팀을 만들려는 팀장이 존재하지 않으면 예외가 발생한다." - {
            // given
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
            val roles = listOf(TeamRole.create("백엔드", 2))
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
                roles = roles,
            )

            // when
            val exception = shouldThrow<IllegalArgumentException> { teamApiService.createTeam(-1L, teamCreate) }

            // then

            exception.message shouldBe "유저 없습니다."
        }
    }
})
