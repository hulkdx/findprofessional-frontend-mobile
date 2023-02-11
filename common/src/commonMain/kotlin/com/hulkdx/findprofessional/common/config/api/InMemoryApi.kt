package com.hulkdx.findprofessional.common.config.api

import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import kotlin.jvm.JvmStatic
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InMemoryApi {
    val module = module {
        single<SignUpApi> { Signup }
    }

    var user: RegisterRequest? = null

    object Signup: SignUpApi {
        override suspend fun register(request: RegisterRequest) {
            user = request
        }
    }

    @JvmStatic
    fun loadKoinModules() {
        loadKoinModules(module)
    }

    @JvmStatic
    fun unloadKoinModules() {
        unloadKoinModules(module)
    }
}
