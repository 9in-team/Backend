package team.guin.team

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.team.dto.TeamCreate
import team.guin.domain.team.dto.TeamRoleCreate
import team.guin.domain.team.dto.TeamTemplateCreate
import team.guin.domain.team.enumeration.HashTagType
import team.guin.domain.team.enumeration.TemplateType

@SpringBootTest
class TeamApiServiceTest(
    private val teamApiService: TeamApiService,
    private val accountApiRepository: AccountApiRepository,
    private val teamApiRepository: TeamApiRepository,
    private val teamRoleApiRepository: TeamRoleApiRepository,
    private val teamTemplateApiRepository: TeamTemplateApiRepository,
    private val hashTagApiRepository: HashTagApiRepository,
    private val templateOptionApiRepository: TemplateOptionApiRepository,

) : FreeSpec({
    "createTeam" - {
        "팀장이 모집글을 작성을 하면 팀이 생성된다." - {
            // given
            val account = Account.create("test@email.com", "nickname", "http://www.wwwwww")
            accountApiRepository.save(account)
            val teamCreate = createTeamRequest()

            // when
            val createTeam = teamApiService.createTeam(
                accountId = account.id,
                teamCreate = teamCreate,
            )

            // then
            val findTeam = teamApiRepository.findByIdOrNull(createTeam.id)
            val findTeamRole = teamRoleApiRepository.findAll()
            val findHashTags = hashTagApiRepository.findAll()
            val findTeamTemplate = teamTemplateApiRepository.findAll()
            val findTemplateOption = templateOptionApiRepository.findAll()

            findTeam?.id shouldBe createTeam.id
            findTeamRole.find { it.team.id == createTeam.id } shouldNotBe null
            findTeamRole.find { it.roleName == "백엔드" && it.roleRequired == 3 } shouldNotBe null
            findTeamRole.find { it.roleName == "프론트" && it.roleRequired == 2 } shouldNotBe null
            findHashTags.find { it.hashTagName == "스프링" } shouldNotBe null
            findHashTags.find { it.hashTagName == "코틀린" } shouldNotBe null
            findTeamTemplate.find { it.templateType == TemplateType.TEXT && it.templateQuestion == "테스트" } shouldNotBe null
            findTeamTemplate.find { it.templateType == TemplateType.RADIOBOX && it.templateQuestion == "라디오테스트" } shouldNotBe null
            findTemplateOption.find { it.optionName == "예" } shouldNotBe null
            findTemplateOption.find { it.optionName == "아니오" } shouldNotBe null
        }
        "팀을 만들려는 유저ID가 존재하지 않으면 예외가 발생한다" - {
            // given
            val teamCreate = createTeamRequest()

            // when
            val exception = shouldThrow<IllegalArgumentException> {
                teamApiService.createTeam(
                    -1,
                    teamCreate,
                )
            }

            // then
            exception.message shouldBe "유저 없음"
        }
    }
})

private fun createTeamRequest(): TeamCreate {
    val hashTagType = HashTagType.PROJECT
    val subject = "스프링 프로젝트"
    val hashTags = mutableListOf("스프링", "코틀린")
    val content = "test"
    val openChattingLink = "http://9in-open-chat"
    var teamCreateRoles: List<TeamRoleCreate> =
        listOf(TeamRoleCreate("백엔드", 3), TeamRoleCreate("프론트", 2))
    var teamCreateTemplate: List<TeamTemplateCreate> = listOf(
        TeamTemplateCreate(TemplateType.TEXT, "테스트"),
        TeamTemplateCreate(TemplateType.RADIOBOX, "라디오테스트", mutableListOf("예", "아니오")),
    )

    return TeamCreate(
        hashtagType = hashTagType,
        teamSubject = subject,
        hashTags = hashTags,
        teamContent = content,
        teamOpenChatUrl = openChattingLink,
        teamTemplates = teamCreateTemplate,
        teamRoles = teamCreateRoles,
    )
}
