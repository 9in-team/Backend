package team.guin.monolithic.infrastructure.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoDetail(
    val id: String,
    val properties: Map<String, Any> = emptyMap(),
    val kakao_account: Map<String, Any> = emptyMap(),
) {
    /* fun convertKakaoUserInfoDTO(jsonData: String): KakaoDetail {
         val mapper = jacksonObjectMapper()
         val kakaoDetail = mapper.readValue(jsonData, KakaoDetail::class.java)
         return kakaoDetail
     }*/
}

//data class KakaoProperties(val nickName:String,val profileImage: String, val thumbnailImage: String)