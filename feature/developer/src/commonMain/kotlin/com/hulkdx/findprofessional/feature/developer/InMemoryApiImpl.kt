package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.feature.home.main.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.pro.auth.signup.SignUpProApi
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
        single<SignUpProApi> { SignUpPro() }
    }

    private var user: RegisterRequest? = null
    private var proUser: RegisterRequest? = null

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
            user = request
            return UserData(
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
        }
    }

    private inner class Login : LoginApi {
        override suspend fun login(request: LoginRequest): UserData {
            if (user?.email == request.email &&
                user?.password == request.password
            ) {
                return UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    User(
                        request.email,
                        firstName = "uiTestFirstName",
                        lastName = "uiTestLastName",
                        profileImage = null,
                    ),
                )
            } else if (
                proUser?.email == request.email &&
                proUser?.password == request.password
            ) {
                return UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    ProUser(
                        email = request.email,
                        firstName = "uiTestFirstName",
                        lastName = "uiTestLastName",
                        coachType = "Life coach",
                        priceNumber = null,
                        priceCurrency = null,
                        profileImageUrl = null,
                        description = null,
                        skypeId = null,
                    ),
                )
            }
            throw RuntimeException("user not found")
        }
    }

    private inner class Pro : ProfessionalApi {
        override suspend fun findAll() = professionals
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

    private inner class SignUpPro : SignUpProApi {
        override suspend fun register(request: SignUpProRequest): UserData {
            return UserData(
                token = Token(
                    accessToken = "esse",
                    refreshToken = "penatibus"
                ),
                user = ProUser(
                    email = "sherri.hodges@example.com",
                    firstName = "Abby Higgins",
                    lastName = "Jeannie Cortez",
                    coachType = "netus",
                    priceNumber = 500,
                    priceCurrency = "EUR",
                    profileImageUrl = "imgur",
                    description = "Some description",
                    skypeId = "sherri.hodges12",
                ),
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

    override fun setProUser(proUser: RegisterRequest) {
        this.user = null
        this.proUser = proUser
    }

    override fun setProfessionals(pro: List<Professional>) {
        professionals = pro
    }

    override fun resetProfessionals() {
        professionals = defaultProfessionals
    }
}
