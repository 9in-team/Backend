package team.guin.security.kakao

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class KakaoApiService {
    fun fetchKakaoUserInfo(accessToken: String): KakaoProfile? {
        val client = WebClient.create()

        return client.get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .bodyToMono(KakaoProfile::class.java)
            .block()
    }
}
