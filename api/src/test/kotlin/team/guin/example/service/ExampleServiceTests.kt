package team.guin.example.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExampleServiceTests(
    private val exampleService: ExampleService,
) : FreeSpec({
    "createExample" - {
        "Example을 저장한다" {
            // given
            val name = "jeong"
            val age = 26

            // when
            val example = exampleService.createExample(name, age)

            // then
            example.id shouldNotBe null
            example.createdAt shouldNotBe null
            example.updatedAt shouldNotBe null
            example.name shouldBe name
            example.age shouldBe age
        }
    }

    "findAllExample" - {
        "목록을 ListResponse에 담아 반환해준다" {
            // given
            val example1 = exampleService.createExample(name = "jeong", age = 123)
            val example2 = exampleService.createExample(name = "god", age = 29)

            // when
            val examples = exampleService.findAllExample()

            // then
            val findExample1 = examples.first { it.id == example1.id }
            findExample1.name shouldBe example1.name
            findExample1.age shouldBe example1.age
            findExample1.createdAt shouldBe example1.createdAt
            findExample1.updatedAt shouldBe example1.updatedAt
            val findExample2 = examples.first { it.id == example2.id }
            findExample2.name shouldBe example2.name
            findExample2.age shouldBe example2.age
            findExample2.createdAt shouldBe example2.createdAt
            findExample2.updatedAt shouldBe example2.updatedAt
        }
    }

    "deleteExample" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleService.createExample(name = "jeong", age = 22)

            // when
            exampleService.deleteExample(example.id)

            // then
            val examples = exampleService.findAllExample()
            examples.find { it.id == example.id } shouldBe null
        }
    }
})
