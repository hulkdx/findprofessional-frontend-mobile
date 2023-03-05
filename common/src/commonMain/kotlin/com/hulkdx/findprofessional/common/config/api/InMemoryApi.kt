package com.hulkdx.findprofessional.common.config.api

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.login.AuthToken
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InMemoryApi {
    val module = module {
        single<SignUpApi> { Signup }
        single<LoginApi> { Login }
    }

    var user: AuthRequest? = null

    object Signup : SignUpApi {
        override suspend fun register(request: AuthRequest): AuthToken {
            user = request
            return AuthToken("uiTestAccessToken", "uiTestRefreshToken")
        }
    }

    object Login : LoginApi {
        override suspend fun login(request: AuthRequest): AuthToken {
            if (request == user) {
                return AuthToken("uiTestAccessToken", "uiTestRefreshToken")
            }
            throw RuntimeException("user not found")
        }
    }

    fun loadKoinModules() {
        loadKoinModules(module)
    }

    fun unloadKoinModules() {
        unloadKoinModules(module)
    }
}
