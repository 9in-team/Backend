package team.guin.monolithic.application.user.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import team.guin.monolithic.application.user.dto.ReqJoinUserDTO
import team.guin.monolithic.application.user.repository.UserRepository
import team.guin.monolithic.domain.user.User

@Service
class UserService(
    val modelMapper: ModelMapper,
    val userRepository: UserRepository,
) {
    fun join(userDTO: ReqJoinUserDTO) {
        val user = modelMapper.map(userDTO, User::class.java)
        userRepository.save(user)
    }
}