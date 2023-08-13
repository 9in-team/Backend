package team.guin.wish

import org.springframework.stereotype.Service
import team.guin.domain.wish.Wish

@Service
class WishApiService(
    private val wishApiRepository: WishApiRepository,
) {
    fun add(wish: Wish): Wish {
        return wishApiRepository.save(wish)
    }
}
