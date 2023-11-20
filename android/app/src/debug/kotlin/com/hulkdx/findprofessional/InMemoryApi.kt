package com.hulkdx.findprofessional

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.common.feature.authentication.logout.logoutModule
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.homeModule
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
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
                )
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
                    )
                )
            }
            throw RuntimeException("user not found")
        }
    }

    object Pro : ProfessionalApi {
        override suspend fun findAll(): List<Professional> {
            return professionals
        }
    }

    fun loadKoinModules() {
        loadKoinModules(module)
    }

    fun unloadKoinModules() {
        unloadKoinModules(module)
        // reload the api modules here
        loadKoinModules(
            listOf(
                loginModule,
                signUpModule,
                logoutModule,
                homeModule,
            )
        )
    }

    fun setUser(email: String, password: String) {
        user = RegisterRequest(email, password, firstName = "", lastName = "", profileImage = null)
    }
}
