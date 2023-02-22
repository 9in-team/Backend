package team.guin.example.service

import org.springframework.stereotype.Service
import team.guin.example.Example
import team.guin.example.repository.ExampleRepository

@Service
class ExampleService(val exampleRepository: ExampleRepository) {
    fun createExample(): Example =
        exampleRepository.save(Example())

    fun findAllExample(): List<Example> =
        exampleRepository.findAll()

    fun findExample(id: Long): Example =
        exampleRepository.findById(id).get()

    fun deleteExample(id: Long): Unit =
        exampleRepository.deleteById(id)
}
