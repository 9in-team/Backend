package team.guin.example.service

import org.springframework.stereotype.Service
import team.guin.example.controller.response.AddResponse
import team.guin.example.controller.response.ListResponse
import team.guin.example.repository.ExampleRepository
import team.guin.example.ExampleEntity

@Service
class ExampleService(val exampleRepo: ExampleRepository) {
    fun add() = AddResponse(exampleRepo.save(ExampleEntity()))

    fun getList() = ListResponse(exampleRepo.findAll())

    fun delete(id: Long) = exampleRepo.deleteById(id)
}
