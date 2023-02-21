package team.guin.example.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.example.service.ExampleService

@RestController
class ExampleController(val exampleService: ExampleService) {
    @PostMapping("/example")
    fun create() = exampleService.create()

    @GetMapping("/example")
    fun showList() = exampleService.getList()

    @GetMapping("/example/{id}")
    fun showOne(@PathVariable id: Long) = exampleService.get(id)

    @DeleteMapping("/example/{id}")
    fun delete(@PathVariable id: Long) = exampleService.delete(id)
}
