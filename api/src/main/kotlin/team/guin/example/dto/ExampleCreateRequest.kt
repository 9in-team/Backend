import io.swagger.annotations.ApiModelProperty

data class ExampleCreateRequest(
    @ApiModelProperty(value = "이름", required = true, example = "Kildong Hong")
    val name: String,

    @ApiModelProperty(value = "예시", required = true, example = "25")
    val age: Int,
)
