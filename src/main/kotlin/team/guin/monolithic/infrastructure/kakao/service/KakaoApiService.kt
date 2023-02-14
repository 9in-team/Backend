package team.guin.monolithic.infrastructure.kakao.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Component
@ConfigurationProperties(prefix = "kakao")
class KakaoApiService {
    @field:NotBlank
    @field:Size(min = 64)
    var kakaoUrl = ""

    fun getUserInfoFromKakaoAccessToken(kakaoAccessToken: String): String {
        val url = URL(kakaoUrl + "v2/user/me")
        val con = url.openConnection() as HttpURLConnection

        con.setRequestProperty("Authorization", "Bearer $kakaoAccessToken")

        val responseCode: Int = con.responseCode
        if (responseCode != HttpStatus.OK.value()) {
            println("실패하였습니다")
        }

        // 응답 한번에 읽어서 string으로 리턴
        val br = BufferedReader(InputStreamReader(con.inputStream))
        val jsonData = br.readText()
        br.close()

        return jsonData
    }
}