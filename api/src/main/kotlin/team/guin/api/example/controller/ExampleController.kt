package team.guin.api.example.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.api.example.controller.request.DeleteRequest
import team.guin.api.example.controller.response.AddResponse
import team.guin.api.example.controller.response.ListResponse
import team.guin.api.example.service.ExampleService

@RestController
@RequestMapping("/example")
class ExampleController(val exampleServ: ExampleService) {
    @PostMapping("/add")
    fun add() = exampleServ.add()

    @GetMapping("/list")
    fun showList() = exampleServ.getList()

    @DeleteMapping("/delete")
    fun delete(@RequestBody deleteReq: DeleteRequest) = exampleServ.delete(deleteReq.id)
}
