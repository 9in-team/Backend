package team.guin.team.dto

data class TeamPartResponse(
    val name: String,
    val requiredCount: Int,
    val hiredCount: Int,
) {
    companion object {
        fun entityToResponse(
            name: String,
            requiredCount: Int,
            hiredCount: Int,
        ): TeamPartResponse {
            return TeamPartResponse(
                name = name,
                requiredCount = requiredCount,
                hiredCount = hiredCount,
            )
        }
    }
}
