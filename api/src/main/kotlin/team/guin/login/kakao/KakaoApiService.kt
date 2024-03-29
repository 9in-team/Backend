package team.guin.login.kakao

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import team.guin.login.kakao.dto.KakaoDetailProfile

@Service
class KakaoApiService {
    fun fetchKakaoDetailProfile(accessToken: String): KakaoDetailProfile {
        val client = WebClient.create()

        return client.get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .bodyToMono(KakaoDetailProfile::class.java)
            .blockOptional()
            .get()
    }
}
