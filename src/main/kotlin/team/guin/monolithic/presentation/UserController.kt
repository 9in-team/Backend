package team.guin.monolithic.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.guin.monolithic.application.user.dto.ReqJoinUserDTO
import team.guin.monolithic.application.user.service.UserService

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/join")
    fun reqJoinUser(@RequestBody joinUserDTO: ReqJoinUserDTO) {
        userService.joinUser(joinUserDTO)
    }
}