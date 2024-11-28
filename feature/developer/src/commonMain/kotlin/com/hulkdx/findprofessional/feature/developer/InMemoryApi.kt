package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest

interface InMemoryApi {
    fun loadKoinModules()
    fun unloadKoinModules()
    fun setUserData(userData: UserData)
    fun setProfessionals(pro: List<Professional>)
    fun resetProfessionals()
}
