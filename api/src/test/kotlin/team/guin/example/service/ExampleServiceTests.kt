package team.guin.example.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.example.service.ExampleService

@SpringBootTest
class ExampleServiceTests(
    private val exampleServ: ExampleService,
) : FreeSpec({
    "create" - {
        "Example을 저장한다" {
            // when
            val addResponse = exampleServ.add()

            // then
            addResponse.added.id shouldNotBe null
        }
    }

    "readAll" - {
        "목록을 ListResponse에 담아 반환해준다" {
            // given
            val example1 = exampleServ.add().added
            val example2 = exampleServ.add().added

            // when
            val listResponse = exampleServ.getList()

            // then
            listResponse.list.find { it.id == example1.id } shouldNotBe null
            listResponse.list.find { it.id == example2.id } shouldNotBe null
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleServ.add().added

            // when
            exampleServ.delete(example.id!!)

            // then
            val listResponse = exampleServ.getList()
            listResponse.list.find { it.id == example.id!! } shouldBe null
        }
    }
})
