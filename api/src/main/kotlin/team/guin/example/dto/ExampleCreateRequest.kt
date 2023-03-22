package team.guin.example.dto

import io.swagger.annotations.ApiModelProperty

data class ExampleCreateRequest(
    @ApiModelProperty("이름", required = true, example = "홍길동")
    val name: String,
    @ApiModelProperty("이름", required = true, example = "25")
    val age: Int,
)
