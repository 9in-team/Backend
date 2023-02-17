package team.guin.api.example.service

import org.springframework.stereotype.Service
import team.guin.api.example.controller.response.AddResponse
import team.guin.api.example.controller.response.ListResponse
import team.guin.api.example.repository.ExampleRepository
import team.guin.domain.example.ExampleEntity

@Service
class ExampleService(val exampleRepo: ExampleRepository) {
    fun add() = AddResponse(exampleRepo.save(ExampleEntity()))

    fun getList() = ListResponse(exampleRepo.findAll())

    fun delete(id: Long) = exampleRepo.deleteById(id)
}
