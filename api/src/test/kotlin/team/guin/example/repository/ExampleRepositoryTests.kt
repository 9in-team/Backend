package team.guin.example.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import team.guin.example.ExampleEntity

@SpringBootTest
class ExampleRepositoryTests(
    private val exampleRepo: ExampleRepository,
) : FreeSpec({
    "save" - {
        "엔티티를 저장한다." {
            // given
            val example = ExampleEntity()

            // when
            exampleRepo.save(example)
            val list = exampleRepo.findAll()

            // then
            list.size shouldBe 1
            list[0].id shouldBe 1
        }
    }

    "delete" - {
        "id로 특정 엔티티를 삭제한다" {
            // given
            val example = exampleRepo.save(ExampleEntity())

            // when
            exampleRepo.deleteById(example.id!!)

            // then
            exampleRepo.findById(example.id!!).isEmpty shouldBe true
        }
    }

    "findAll" - {
        "저장된 모든 엔티티를 가져온다" {
            // given
            exampleRepo.deleteAll()
            val example1 = exampleRepo.save(ExampleEntity())
            val example2 = exampleRepo.save(ExampleEntity())

            // when
            val list = exampleRepo.findAll()

            // then
            list.size shouldBe 2
            list.find { it.id == example1.id } shouldNotBe null
            list.find { it.id == example2.id } shouldNotBe null
        }
    }
})
