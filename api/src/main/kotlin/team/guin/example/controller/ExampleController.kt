package team.guin.example.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.example.service.ExampleService

@RestController
class ExampleController(val exampleServ: ExampleService) {
    @PostMapping("/example")
    fun add() = exampleServ.add()

    @GetMapping("/example")
    fun showList() = exampleServ.getList()

    @GetMapping("/example/{id}")
    fun showOne(@PathVariable id: Long) = exampleServ.get(id)

    @DeleteMapping("/example/{id}")
    fun delete(@PathVariable id: Long) = exampleServ.delete(id)
}
