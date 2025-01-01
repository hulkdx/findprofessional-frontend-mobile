package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.features.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.features.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.Token
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.review.ReviewApi
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.koin.dsl.module

internal class InMemoryApiImpl : InMemoryApi {
    private val module = module {
        single<SignUpApi> { Signup() }
        single<LoginApi> { Login() }
        single<ProfessionalApi> { Pro() }
        single<ReviewApi> { Review() }
    }

    private var users = mutableListOf<UserData>()

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

    private val defaultProfessionals = listOf(
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
                    date = LocalDate.now(),
                    from = LocalTime.parse("08:00"),
                    to = LocalTime.parse("08:30"),
                ),
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

    private var professionals = defaultProfessionals

    private inner class Signup : SignUpApi {
        override suspend fun register(request: RegisterRequest): UserData {
            users.add(
                UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    User(
                        request.email,
                        request.firstName,
                        request.lastName,
                        profileImage = null,
                    ),
                )
            )
            return users.last()
        }
    }

    private inner class Login : LoginApi {
        override suspend fun login(request: LoginRequest): UserData {
            return users.first {
                val userEmail = (it.user as? ProUser)?.email ?: (it.user as? User)?.email
                userEmail == request.email
            }
        }
    }

    private inner class Pro : ProfessionalApi {
        override suspend fun findAll() = professionals

        override suspend fun register(request: SignUpProRequest): UserData {
            users.add(
                UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    ProUser(
                        request.email,
                        request.firstName,
                        request.lastName,
                    ),
                )
            )
            return users.last()
        }

        override suspend fun update(proUser: ProUser) {
            users.removeLast()
            users.add(
                UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    proUser,
                )
            )
        }
    }

    private inner class Review : ReviewApi {
        override suspend fun findAll(
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

    override fun setUserData(userData: UserData) {
        this.users.clear()
        this.users.add(userData)
    }

    override fun setProfessionals(pro: List<Professional>) {
        professionals = pro
    }

    override fun resetProfessionals() {
        professionals = defaultProfessionals
    }
}
