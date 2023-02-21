package team.guin.example.service

import org.springframework.stereotype.Service
import team.guin.example.Example
import team.guin.example.repository.ExampleRepository

@Service
class ExampleService(val exampleRepository: ExampleRepository) {
    fun create() = exampleRepository.save(Example())

    fun findList() = exampleRepository.findAll()

    fun get(id: Long) = exampleRepository.findById(id)

    fun delete(id: Long) = exampleRepository.deleteById(id)
}
