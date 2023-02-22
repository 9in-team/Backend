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
            val addResponse = exampleService.createExample()

            // then
            addResponse.id shouldNotBe null
        }
    }

    "readAll" - {
        "목록을 ListResponse에 담아 반환해준다" {
            // given
            val example1 = exampleService.createExample()
            val example2 = exampleService.createExample()

            // when
            val listResponse = exampleService.findAllExample()

            // then
            listResponse.find { it.id == example1.id } shouldNotBe null
            listResponse.find { it.id == example2.id } shouldNotBe null
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleService.createExample()

            // when
            exampleService.deleteExample(example.id!!)

            // then
            val listResponse = exampleService.findAllExample()
            listResponse.find { it.id == example.id!! } shouldBe null
        }
    }
})
