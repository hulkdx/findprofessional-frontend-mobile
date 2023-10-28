package com.hulkdx.findprofessional.common.config.api

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.common.feature.authentication.logout.logoutModule
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.Review
import com.hulkdx.findprofessional.common.feature.home.homeModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InMemoryApi {
    val module = module {
        single<SignUpApi> { Signup }
        single<LoginApi> { Login }
        single<ProfessionalApi> { Pro }
    }

    var user: AuthRequest? = null

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
            availabilities = listOf(
                listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                listOf("00-04", "0", "0", "0", "0", "0", "0"),
                listOf("04-08", "0", "1", "0", "0", "0", "0"),
                listOf("08-12", "0", "0", "2", "0", "0", "0"),
                listOf("12-16", "0", "0", "0", "3", "0", "0"),
                listOf("16-20", "0", "0", "0", "0", "0", "0"),
                listOf("20-24", "0", "0", "0", "0", "4", "0"),
            ),
            reviews = listOf(
                Review(
                    profileImageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
                    firstName = "Stefan",
                    lastName = "Holman",
                    star = 5,
                    text = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\n" +
                            "feeling like I’ve learned a lot.",
                    date = "Sep 18, 2023",
                ),
                Review(
                    profileImageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
                    firstName = "Stefan",
                    lastName = "Holman",
                    star = 5,
                    text = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\n" +
                            "feeling like I’ve learned a lot.",
                    date = "Sep 18, 2023",
                ),
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
            availabilities = listOf(
                listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                listOf("00-04", "0", "0", "0", "0", "0", "0"),
                listOf("04-08", "0", "1", "0", "0", "0", "0"),
                listOf("08-12", "0", "0", "2", "0", "0", "0"),
                listOf("12-16", "0", "0", "0", "3", "0", "0"),
                listOf("16-20", "0", "0", "0", "0", "0", "0"),
                listOf("20-24", "0", "0", "0", "0", "4", "0"),
            ),
            reviews = listOf(
                Review(
                    profileImageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
                    firstName = "Stefan",
                    lastName = "Holman",
                    star = 5,
                    text = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\n" +
                            "feeling like I’ve learned a lot.",
                    date = "Sep 18, 2023",
                ),
                Review(
                    profileImageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
                    firstName = "Stefan",
                    lastName = "Holman",
                    star = 5,
                    text = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\n" +
                            "feeling like I’ve learned a lot.",
                    date = "Sep 18, 2023",
                ),
            )
        )
    )

    object Signup : SignUpApi {
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

    object Login : LoginApi {
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
}
