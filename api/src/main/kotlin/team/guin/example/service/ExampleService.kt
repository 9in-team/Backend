package team.guin.example.service

import org.springframework.stereotype.Service
import team.guin.example.ExampleEntity
import team.guin.example.controller.response.AddResponse
import team.guin.example.controller.response.ListResponse
import team.guin.example.repository.ExampleRepository

@Service
class ExampleService(val exampleRepo: ExampleRepository) {
    fun add() = AddResponse(exampleRepo.save(ExampleEntity()))

    fun getList() = ListResponse(exampleRepo.findAll())

    fun get(id: Long) = exampleRepo.findById(id)

    fun delete(id: Long) = exampleRepo.deleteById(id)
}
