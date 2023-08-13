package team.guin.wish

import org.springframework.stereotype.Service

@Service
class WishApiService(
    private val wishApiRepository: WishApiRepository,
) {
    fun add(wish: Wish): Wish {
        return wishApiRepository.save(wish)
    }
}
