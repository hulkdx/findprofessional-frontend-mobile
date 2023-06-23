package com.hulkdx.findprofessional.common.config.api

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
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

    object Pro: ProfessionalApi {
        override suspend fun findAll(): List<Professional> {
            return listOf(
                Professional(
                    1,
                    "test@email.com",
                    "Luba",
                    "Mikaela",
                    "Life coach",
                    100,
                    "EUR",
                    "https://i.imgur.com/5Yma8Kl.jpeg"
                )
            )
        }
    }

    fun loadKoinModules() {
        loadKoinModules(module)
    }

    fun unloadKoinModules() {
        unloadKoinModules(module)
    }
}
