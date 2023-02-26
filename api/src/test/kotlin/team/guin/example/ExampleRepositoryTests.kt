package team.guin.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.domain.example.Example

@SpringBootTest
class ExampleRepositoryTests(
    private val exampleRepository: ExampleRepository,
) : FreeSpec({
    "save" - {
        "엔티티를 저장한다." {
            // given
            val example = Example(name = "jeong", age = 20)

            // when
            val savedExample = exampleRepository.save(example)

            // then
            val result = exampleRepository.findById(savedExample.id).get()
            result.id shouldNotBe null
            result.createdAt shouldNotBe null
            result.updatedAt shouldNotBe null
            result.age shouldBe example.age
            result.name shouldBe example.name
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleRepository.save(Example(name = "min", age = 12))

            // when
            exampleRepository.deleteById(example.id)

            // then
            val exception = shouldThrow<NoSuchElementException> {
                exampleRepository.findById(example.id).get()
            }
            exception.message shouldBe "No value present"
        }
    }
    "deleteById" - {
        "특정 엔티티를 삭제하면 해당 엔티티는 조회되지 않는다" {
            // given
            val example1 = Example(name = "jeong", age = 25)
            val example2 = Example(name = "hoe", age = 12)
            val savedExample1 = exampleRepository.save(example1)
            val savedExample2 = exampleRepository.save(example2)

            // when
            exampleRepository.deleteById(savedExample1.id)

            // then
            val examples = exampleRepository.findAll()
            examples.find { it.id == example1.id } shouldBe null
            examples.find { it.id == example2.id } shouldNotBe null
            savedExample2.deletedAt shouldBe null
            savedExample2.age shouldBe 12
            savedExample2.name shouldBe "hoe"
            savedExample2.id shouldBe example2.id
        }
    }
})
