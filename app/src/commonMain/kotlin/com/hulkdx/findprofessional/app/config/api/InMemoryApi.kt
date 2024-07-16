package com.hulkdx.findprofessional.app.config.api

import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest

interface InMemoryApi {
    fun loadKoinModules()
    fun unloadKoinModules()
    fun setUser(user: RegisterRequest)
}
