package team.guin.example.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExampleServiceTests(
    private val exampleService: ExampleService,
) : FreeSpec({
    "create" - {
        "Example을 저장한다" {
            // when
            val addResponse = exampleService.create()

            // then
            addResponse.added.id shouldNotBe null
        }
    }

    "readAll" - {
        "목록을 ListResponse에 담아 반환해준다" {
            // given
            val example1 = exampleService.create().added
            val example2 = exampleService.create().added

            // when
            val listResponse = exampleService.getList()

            // then
            listResponse.list.find { it.id == example1.id } shouldNotBe null
            listResponse.list.find { it.id == example2.id } shouldNotBe null
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleService.create().added

            // when
            exampleService.delete(example.id!!)

            // then
            val listResponse = exampleService.getList()
            listResponse.list.find { it.id == example.id!! } shouldBe null
        }
    }
})
