package team.guin.security.kakao

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class KakaoApiService {
    fun fetchKakaoUserInfo(accessToken: String): KakaoUserInfo? {
        val url = URL("https://kapi.kakao.com/v2/user/me")
        val con = url.openConnection() as HttpURLConnection
        con.setRequestProperty("Authorization", "Bearer $accessToken")

        val responseCode: Int = con.responseCode
        if (responseCode != HttpStatus.OK.value()) {
            return null
        }

        val br = BufferedReader(InputStreamReader(con.inputStream))

        val json = br.readText()
        val jsonTree = jacksonObjectMapper().readTree(json)

        val email = jsonTree.get("kakao_account")?.get("email")?.asText() ?: return null
        val nickname = jsonTree.get("kakao_account")?.get("profile")?.get("nickname")?.asText() ?: return null
        val profileImageUrl = jsonTree.get("kakao_account")?.get("profile")?.get("profile_image_url")?.asText() ?: ""

        return KakaoUserInfo(email, nickname, profileImageUrl)
    }
}
