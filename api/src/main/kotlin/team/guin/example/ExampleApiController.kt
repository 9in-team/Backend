package team.guin.example

import ExampleCreateRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.guin.domain.example.Example

@RequestMapping("/example")
@RestController
@Api(value = "Example Api", tags = ["Example"])
class ExampleApiController(val exampleService: ExampleService) {
    @PostMapping
    @ApiOperation(value = "예제용 새로운 사용자를 생성합니다. ")
    private fun createExample(@RequestBody request: ExampleCreateRequest): Example =
        exampleService.createExample(request.name, request.age)

    @GetMapping
    @ApiOperation(value = "예제용 모든 사용자의 목록을 가져옵니다.")
    fun findAllExample(): List<Example> =
        exampleService.findAllExample()

    @GetMapping("/{id}")
    @ApiOperation(value = "예제용 ID에 해당하는 사용자를 가져옵니다.")
    fun findExample(@PathVariable id: Long): Example =
        exampleService.findExample(id)

    @DeleteMapping("/{id}")
    @ApiOperation(value = "예제용 ID에 해당하는 사용자를 삭제합니다.")
    fun deleteExample(@PathVariable id: Long): Unit =
        exampleService.deleteExample(id)
}
