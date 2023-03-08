package team.guin.security

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.GrantedAuthority
import team.guin.account.AccountApiRepository
import team.guin.domain.account.Account
import team.guin.domain.account.enumeration.AccountRole
import team.guin.security.kakao.AccountProfile
import team.guin.security.kakao.KakaoAuthenticationManager
import team.guin.security.kakao.KakaoAuthenticationToken

@SpringBootTest
class KakaoAuthenticationManagerTests(
    private val accountApiRepository: AccountApiRepository,
    private val kakaoAuthenticationManager: KakaoAuthenticationManager,
) : FreeSpec({
    "authenticate" - {
        "Account.AccountRole이 Authentication.authorities에 제대로 추가된다" {
            // given
            withContext(Dispatchers.IO) {
                accountApiRepository.deleteAll()
            }

            val account = Account.create("email@a.com", "nickname", "imageUrl")
            account.accountRole = AccountRole.ADMIN
            val savedAccount = withContext(Dispatchers.IO) {
                accountApiRepository.save(account)
            }

            val accountProfile = AccountProfile(savedAccount.email, savedAccount.nickname, savedAccount.imageId)
            val beforeAuth = KakaoAuthenticationToken(accountProfile)

            // when
            val afterAuth = kakaoAuthenticationManager.authenticate(beforeAuth)

            // then
            val authorities = (afterAuth.authorities as MutableList<*>)
            val authority = authorities[0] as GrantedAuthority
            authority.toString() shouldBe "ADMIN"
        }

        "첫 로그인 시 엔티티를 저장하고 저장한 정보를 불러온다" {
            // given
            withContext(Dispatchers.IO) {
                accountApiRepository.deleteAll()
            }

            val email = "email@a.com"
            val nickname = "nickname"
            val imageUrl = "imageUrl"

            val accountProfile = AccountProfile(email, nickname, imageUrl)
            val beforeAuth = KakaoAuthenticationToken(accountProfile)

            // when
            val afterAuth = kakaoAuthenticationManager.authenticate(beforeAuth)

            // then
            val account = withContext(Dispatchers.IO) {
                accountApiRepository.findByEmail("email@a.com")
            }
            account shouldNotBe null
            account?.nickname shouldBe nickname
            account?.imageId shouldBe imageUrl

            val afterAccountProfile = afterAuth.details as AccountProfile
            afterAccountProfile.id shouldBe account?.id
            afterAccountProfile.email shouldBe email
            afterAccountProfile.nickname shouldBe nickname
            afterAccountProfile.imageUrl shouldBe imageUrl
        }

        "첫 로그인이 아니라면 엔티티 기존 정보를 불러온다" {
            // given
            withContext(Dispatchers.IO) {
                accountApiRepository.deleteAll()
            }

            val firstProfile = AccountProfile("email@a.com", "nickname", "imageUrl")
            val firstAuth = KakaoAuthenticationToken(firstProfile)

            val secondProfile = AccountProfile("email@a.com", "bad", "bad")
            val secondAuth = KakaoAuthenticationToken(secondProfile)

            // when
            kakaoAuthenticationManager.authenticate(firstAuth)
            val afterSecondAuth = kakaoAuthenticationManager.authenticate(secondAuth)

            // then
            val afterSecondProfile = afterSecondAuth.details as AccountProfile
            afterSecondProfile.nickname shouldBe firstProfile.nickname
            afterSecondProfile.imageUrl shouldBe firstProfile.imageUrl
        }
    }
})
