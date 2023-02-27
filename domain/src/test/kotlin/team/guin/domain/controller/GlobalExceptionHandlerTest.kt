package team.guin.domain.controller

import io.kotest.core.spec.style.FreeSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest(val mockMvc: MockMvc) : FreeSpec({
    "Exception" - {
        "테스트 이셉션 요청을 보내면 중복된 이메일 예외를 반환한다." {
            mockMvc.perform(
                get("/domain/example/exception"),
            ).andExpect(
                status().is4xxClientError,
            ).andExpectAll(
                jsonPath("\$.message").value("중복된 이메일입니다. 다시 입력해주세요."),
            ).andDo(
                MockMvcResultHandlers.print(),
            )
        }
    }
})
