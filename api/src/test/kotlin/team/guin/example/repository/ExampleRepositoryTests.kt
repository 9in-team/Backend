package team.guin.example.repository

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
            exampleRepository.save(example)

            // then
            val list = exampleRepository.findAll()
            list.size shouldBe 1
            list[0].id shouldBe 1
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleRepository.save(Example())

            // when
            exampleRepository.deleteById(example.id!!)

            // then
            exampleRepository.findById(example.id!!).isEmpty shouldBe true
        }
    }

    "findAll" - {
        "저장된 모든 엔티티를 가져온다" {
            // given
            exampleRepository.deleteAll()
            val example1 = exampleRepository.save(Example())
            val example2 = exampleRepository.save(Example())

            // when
            val list = exampleRepository.findAll()

            // then
            list.size shouldBe 2
            list.find { it.id == example1.id } shouldNotBe null
            list.find { it.id == example2.id } shouldNotBe null
        }
    }
})
