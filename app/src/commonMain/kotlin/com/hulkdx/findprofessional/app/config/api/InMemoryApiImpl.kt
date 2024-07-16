package com.hulkdx.findprofessional.app.config.api

import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.login.model.Token
import com.hulkdx.findprofessional.feature.authentication.login.model.User
import com.hulkdx.findprofessional.feature.authentication.login.model.UserData
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.feature.home.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.home.model.ProfessionalReview
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.koin.dsl.module

internal class InMemoryApiImpl : InMemoryApi {
    private val module = module {
        single<SignUpApi> { Signup() }
        single<LoginApi> { Login() }
        single<ProfessionalApi> { Pro() }
    }

    private var user: RegisterRequest? = null

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

    private val todayAvailableTime = ProfessionalAvailability(
        date = LocalDate.now(),
        from = LocalTime.parse("08:00"),
        to = LocalTime.parse("08:30"),
    )

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
            "Former professional boxer who competed from 1985 to 2005",
            availability = listOf(
                todayAvailableTime,
                ProfessionalAvailability(
                    date = LocalDate.now(),
                    from = LocalTime.parse("09:00"),
                    to = LocalTime.parse("10:30"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-10"),
                    from = LocalTime.parse("07:00"),
                    to = LocalTime.parse("08:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-11"),
                    from = LocalTime.parse("09:00"),
                    to = LocalTime.parse("11:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-12"),
                    from = LocalTime.parse("12:00"),
                    to = LocalTime.parse("15:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-13"),
                    from = LocalTime.parse("20:00"),
                    to = LocalTime.parse("00:00"),
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
                    date = LocalDate.parse("2023-11-09"),
                    from = LocalTime.parse("08:00"),
                    to = LocalTime.parse("09:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-10"),
                    from = LocalTime.parse("07:00"),
                    to = LocalTime.parse("08:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-11"),
                    from = LocalTime.parse("09:00"),
                    to = LocalTime.parse("11:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-12"),
                    from = LocalTime.parse("12:00"),
                    to = LocalTime.parse("15:00"),
                ),
                ProfessionalAvailability(
                    date = LocalDate.parse("2023-11-13"),
                    from = LocalTime.parse("20:00"),
                    to = LocalTime.parse("00:00"),
                ),
            ),
            reviewSize = "100",
            reviews = reviews,
        ),
        Professional(
            3,
            "test2@email.com",
            "Naomi",
            "Spence",
            "Life coach",
            200,
            "EUR",
            "https://i.imgur.com/FVABZOc.jpeg",
            null,
            "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
            availability = listOf(),
            reviewSize = "100",
            reviews = listOf(),
        ),
        Professional(
            4,
            "test2@email.com",
            "Naomi",
            "Spence",
            "Life coach",
            200,
            "EUR",
            "https://i.imgur.com/FVABZOc.jpeg",
            null,
            "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
            availability = listOf(),
            reviewSize = "100",
            reviews = listOf(),
        )
    )

    private inner class Signup : SignUpApi {
        override suspend fun register(request: RegisterRequest): UserData {
            user = request
            return UserData(
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

    private inner class Login : LoginApi {
        override suspend fun login(request: LoginRequest): UserData {
            if (request == LoginRequest(user?.email ?: "", user?.password ?: "")) {
                return UserData(
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

    private inner class Pro : ProfessionalApi {
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

    override fun loadKoinModules() {
        org.koin.core.context.loadKoinModules(module)
    }

    override fun unloadKoinModules() {
        org.koin.core.context.unloadKoinModules(module)
    }

    override fun setUser(user: RegisterRequest) {
        this.user = user
    }
}
