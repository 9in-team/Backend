package team.guin.example.repository

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.example.Example

@SpringBootTest
class ExampleRepositoryTests(
    private val exampleRepository: ExampleRepository,
) : FreeSpec({
    "save" - {
        "엔티티를 저장한다." {
            // given
            val example = Example()

            // when
            val savedExample = exampleRepository.save(example)

            // then
            val result = exampleRepository.findById(savedExample.id).get()
            result.id shouldNotBe null
            result.createdAt shouldNotBe null
            result.updatedAt shouldNotBe null
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleRepository.save(Example())

            // when
            exampleRepository.deleteById(example.id)

            // then
            val exception = shouldThrow<NoSuchElementException> {
                exampleRepository.findById(example.id).get()
            }
            exception.message shouldBe "No value present"
        }
    }
})
