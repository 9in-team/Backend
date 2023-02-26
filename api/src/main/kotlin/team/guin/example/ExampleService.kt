package team.guin.example

import org.springframework.stereotype.Service
import team.guin.domain.example.Example

@Service
class ExampleService(val exampleRepository: ExampleRepository) {
    fun createExample(name: String, age: Int): Example =
        exampleRepository.save(Example(name, age))

    fun findAllExample(): List<Example> =
        exampleRepository.findAll()

    fun findExample(id: Long): Example =
        exampleRepository.findById(id).get()

    fun deleteExample(id: Long): Unit =
        exampleRepository.deleteById(id)
}
