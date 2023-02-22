package team.guin.example.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.example.controller.response.AddResponse
import team.guin.example.controller.response.CommonListResponse
import team.guin.example.service.ExampleService

@RequestMapping("/example")
@RestController
class ExampleApiController(val exampleService: ExampleService) {
    @PostMapping
    fun create() = AddResponse(exampleService.create())

    @GetMapping
    fun showList() = CommonListResponse(exampleService.findList())

    @GetMapping("/{id}")
    fun showOne(@PathVariable id: Long) = exampleService.get(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = exampleService.delete(id)
}
