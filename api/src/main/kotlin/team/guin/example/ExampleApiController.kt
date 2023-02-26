package team.guin.example

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.domain.example.Example
import team.guin.example.dto.ExampleCreateRequest

@RequestMapping("/example")
@RestController
class ExampleApiController(val exampleService: ExampleService) {
    @PostMapping
    fun createExample(@RequestBody request: ExampleCreateRequest): Example =
        exampleService.createExample(request.name, request.age)

    @GetMapping
    fun findAllExample(): List<Example> =
        exampleService.findAllExample()

    @GetMapping("/{id}")
    fun findExample(@PathVariable id: Long): Example =
        exampleService.findExample(id)

    @DeleteMapping("/{id}")
    fun deleteExample(@PathVariable id: Long): Unit =
        exampleService.deleteExample(id)
}
