package team.guin.example.service

import org.springframework.stereotype.Service
import team.guin.example.Example
import team.guin.example.repository.ExampleRepository

@Service
class ExampleService(val exampleRepository: ExampleRepository) {
    fun createExample() = exampleRepository.save(Example())

    fun findAllExample() = exampleRepository.findAll()

    fun findExample(id: Long) = exampleRepository.findById(id)

    fun deleteExample(id: Long) = exampleRepository.deleteById(id)
}
