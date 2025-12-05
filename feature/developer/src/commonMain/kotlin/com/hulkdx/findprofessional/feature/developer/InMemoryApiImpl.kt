package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.Token
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.features.user.UserType
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.feature.profile.edit.api.UpdateProfileApi
import com.hulkdx.findprofessional.feature.review.ReviewApi
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import org.koin.dsl.module

internal class InMemoryApiImpl : InMemoryApi {
    private val module = module {
        single<SignUpApi> { Signup() }
        single<LoginApi> { Login() }
        single<ProfessionalApi> { Pro() }
        single<ReviewApi> { Review() }
        single<UpdateProfileApi> { UpdateProfile() }
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
                    id = 1,
                    from = Instant.parse("2024-01-01T08:00:00Z"),
                    to = Instant.parse("2024-01-01T08:30:00Z"),
                ),
                ProfessionalAvailability(
                    id = 2,
                    from = Instant.parse("2024-01-01T09:00:00Z"),
                    to = Instant.parse("2024-01-01T10:30:00Z"),
                ),
                ProfessionalAvailability(
                    id = 3,
                    from = Instant.parse("2023-11-10T07:00:00Z"),
                    to = Instant.parse("2023-11-10T08:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 4,
                    from = Instant.parse("2023-11-11T09:00:00Z"),
                    to = Instant.parse("2023-11-11T11:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 5,
                    from = Instant.parse("2023-11-12T12:00:00Z"),
                    to = Instant.parse("2023-11-12T15:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 6,
                    from = Instant.parse("2023-11-13T20:00:00Z"),
                    to = Instant.parse("2023-11-14T00:00:00Z"),
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
                    id = 7,
                    from = Instant.parse("2023-11-09T08:00:00Z"),
                    to = Instant.parse("2023-11-09T09:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 8,
                    from = Instant.parse("2023-11-10T07:00:00Z"),
                    to = Instant.parse("2023-11-10T08:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 9,
                    from = Instant.parse("2023-11-11T09:00:00Z"),
                    to = Instant.parse("2023-11-11T11:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 10,
                    from = Instant.parse("2023-11-12T12:00:00Z"),
                    to = Instant.parse("2023-11-12T15:00:00Z"),
                ),
                ProfessionalAvailability(
                    id = 11,
                    from = Instant.parse("2023-11-13T20:00:00Z"),
                    to = Instant.parse("2023-11-14T00:00:00Z"),
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

    private var availability = mutableListOf<ProfessionalAvailability>()

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
            if (users.isNotEmpty()) {
                users.removeAt(users.size - 1)
            }
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

        override suspend fun getAvailability() = availability
        override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
            availability.addAll(
                request.items.map {
                    ProfessionalAvailability(
                        id = 1,
                        from = it.from,
                        to = it.to,
                    )
                }
            )
        }

        override suspend fun createBooking(
            request: CreateBookingRequest,
        ): CreateBookingResponse {
            return CreateBookingResponse("", "", "", "")
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

    private inner class UpdateProfile : UpdateProfileApi {
        override suspend fun update(user: User): UserType {
            if (users.isNotEmpty()) {
                users.removeAt(users.size - 1)
            }
            users.add(
                UserData(
                    Token(
                        "uiTestAccessToken",
                        "uiTestRefreshToken",
                    ),
                    user,
                )
            )
            return user
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

    override fun setProAvailability(availability: List<ProfessionalAvailability>) {
        this.availability = availability.toMutableList()
    }
}
