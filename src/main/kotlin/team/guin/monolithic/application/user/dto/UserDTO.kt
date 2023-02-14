package team.guin.monolithic.application.user.dto

data class ReqLoginDTO(var kakaoAccessToken: String)
data class ReqJoinUserDTO(val email: String, val nickname: String, val imageId: String)