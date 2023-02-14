package team.guin.monolithic.infrastructure.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class KakaoDetail(
    val properties: Map<String, Any> = emptyMap(),
    val kakao_account: Map<String, Any> = emptyMap(),
) {
    companion object {
        fun from(jsonData: String): KakaoDetail {
            val mapper = jacksonObjectMapper()
            val kakaoDetail = mapper.readValue(jsonData, KakaoDetail::class.java)
            return kakaoDetail
        }
    }
}