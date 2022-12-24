package team.guin.monolithic.example

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ExampleHandler {

    fun hello(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(BodyInserters.fromValue("hello"))
}