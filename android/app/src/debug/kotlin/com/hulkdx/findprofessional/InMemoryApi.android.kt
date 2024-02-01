package com.hulkdx.findprofessional

import com.hulkdx.findprofessional.common.di.allModules
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

object InMemoryApi {
    val module = module {
        single<SignUpApi> { Signup }
        single<LoginApi> { Login }
        single<ProfessionalApi> { Pro }
    }

    var user: RegisterRequest? = null

    private val reviews = listOf(
        ProfessionalReview(
            id = 0,
            user = User(
                email = "kasey.harper@example.com",
                firstName = "Vilma",
                lastName = "Rory",
                profileImage = "https://i.imgur.com/D99rBXe.jpeg"
            ),
            rate = 5,
            contentText = "It was really great",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        ProfessionalReview(
            id = 1,
            user = User(
                email = "kasey.harper@example.com",
                profileImage = "https://i.imgur.com/A755ZPz.jpeg",
                firstName = "Elwood",
                lastName = "Agnes"
            ),
            rate = 4,
            contentText = "Not bad",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        )
    )

    val professionals = listOf(
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
            "Former professional boxer who competed from 1985 to 2005",
            availability = listOf(
                ProfessionalAvailability(
                    date = "2024-01-31".toLocalDate(),
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
            reviewSize = "100",
            reviews = reviews
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
            "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
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
            reviewSize = "100",
            reviews = reviews,
        )
    )

    object Signup : SignUpApi {
        override suspend fun register(request: RegisterRequest): Auth {
            user = request
            return Auth(
                Token(
                    "uiTestAccessToken",
                    "uiTestRefreshToken",
                ),
                User(
                    "uiTestEmail",
                    firstName = "uiTestFirstName",
                    lastName = "uiTestLastName",
                    profileImage = null,
                ),
            )
        }
    }

    object Login : LoginApi {
        override suspend fun login(request: LoginRequest): Auth {
            if (request == LoginRequest(user?.email ?: "", user?.password ?: "")) {
                return Auth(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    User(
                        "uiTestEmail",
                        firstName = "uiTestFirstName",
                        lastName = "uiTestLastName",
                        profileImage = null,
                    ),
                )
            }
            throw RuntimeException("user not found")
        }
    }

    object Pro : ProfessionalApi {
        override suspend fun findAll() = professionals
        override suspend fun findAllReviews(
            professionalId: Int,
            page: Int,
            pageSize: Int,
        ) = (((page - 1) * pageSize)..<(page * pageSize)).map {
            ProfessionalReview(
                id = it.toLong(),
                user = User(
                    email = "$it@example.com",
                    firstName = "$it",
                    lastName = "$it",
                    profileImage = "https://i.imgur.com/D99rBXe.jpeg"
                ),
                rate = 5,
                contentText = "It was really great",
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            )
        }
    }

    fun loadKoinModules() {
        loadKoinModules(module)
    }

    fun unloadKoinModules() {
        unloadKoinModules(module)
        // reload the api modules here
        loadKoinModules(allModules())
    }

    fun setUser(email: String, password: String) {
        user = RegisterRequest(email, password, firstName = "", lastName = "")
    }
}
