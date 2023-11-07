package com.hulkdx.findprofessional.common.uitests

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.concurrent.ThreadLocal


@OptIn(ExperimentalObjCName::class)
@ThreadLocal
object InMemoryApi {

    private val module = module {
        single<SignUpApi> { Signup }
        single<LoginApi> { Login }
        single<ProfessionalApi> { Pro }
    }

    private var user: AuthRequest? = null

    private val professionals = listOf(
        Professional(
            1,
            "test@email.com",
            "Luba",
            "Mikaela",
            "Life coach",
            100,
            "EUR",
            "https://i.imgur.com/5Yma8Kl.jpeg",
            "5.0",
            "former professional boxer who competed from 1985 to 2005",
            availability = listOf(
                ProfessionalAvailability(
                    date = "2023-11-09".toLocalDate(),
                    from = "08:00".toLocalTime(),
                    to = "09:00".toLocalTime(),
                ),
                ProfessionalAvailability(
                    date = "2023-11-10".toLocalDate(),
                    from = "07:00".toLocalTime(),
                    to = "08:00".toLocalTime(),
                ),
                ProfessionalAvailability(
                    date = "2023-11-11".toLocalDate(),
                    from = "09:00".toLocalTime(),
                    to = "11:00".toLocalTime(),
                ),
                ProfessionalAvailability(
                    date = "2023-11-12".toLocalDate(),
                    from = "12:00".toLocalTime(),
                    to = "15:00".toLocalTime(),
                ),
                ProfessionalAvailability(
                    date = "2023-11-13".toLocalDate(),
                    from = "20:00".toLocalTime(),
                    to = "00:00".toLocalTime(),
                ),
            ),
            reviews = ProfessionalReview(
                100,
                listOf(
                    ProfessionalReview.Content(
                        userProfileImageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
                        userFirstName = "Stefan",
                        userLastName = "Holman",
                        star = 5,
                        reviewText = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\nfeeling like I’ve learned a lot.",
                        reviewDate = "Sep 18, 2023",
                    ),
                )
            )
        ),
        Professional(
            2,
            "test2@email.com",
            "Naomi",
            "Spence",
            "Life coach",
            200,
            "EUR",
            "https://i.imgur.com/FVABZOc.jpeg",
            null,
            "actress from 1985 to 2005",
            availability = listOf(),
        )
    )

    private object Signup : SignUpApi {
        override suspend fun register(request: AuthRequest): Auth {
            user = request
            return Auth(
                Token(
                    "uiTestAccessToken",
                    "uiTestRefreshToken",
                ),
                User(
                    "uiTestEmail",
                )
            )
        }
    }

    private object Login : LoginApi {
        override suspend fun login(request: AuthRequest): Auth {
            if (request == user) {
                return Auth(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    User(
                        "uiTestEmail",
                    )
                )
            }
            throw RuntimeException("user not found")
        }
    }

    private object Pro : ProfessionalApi {
        override suspend fun findAll(): List<Professional> {
            return professionals
        }
    }

    fun loadKoinModules() {
        loadKoinModules(module)
    }

    fun setUser(@ObjCName("_") user: AuthRequest) {
        InMemoryApi.user = user
    }
}
