package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest

interface InMemoryApi {
    fun loadKoinModules()
    fun unloadKoinModules()
    fun setUser(user: RegisterRequest)
}
