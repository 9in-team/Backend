package team.guin.example.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.example.Example
import team.guin.example.controller.response.AddResponse
import team.guin.example.controller.response.CommonListResponse
import team.guin.example.service.ExampleService
import java.util.Optional

@RequestMapping("/example")
@RestController
class ExampleApiController(val exampleService: ExampleService) {
    @PostMapping
    fun createExample(): AddResponse =
        AddResponse(exampleService.createExample())

    @GetMapping
    fun findAllExample(): CommonListResponse<MutableList<Example>> =
        CommonListResponse(exampleService.findAllExample())

    @GetMapping("/{id}")
    fun findExample(@PathVariable id: Long): Optional<Example> =
        exampleService.findExample(id)

    @DeleteMapping("/{id}")
    fun deleteExample(@PathVariable id: Long): Unit =
        exampleService.deleteExample(id)
}
