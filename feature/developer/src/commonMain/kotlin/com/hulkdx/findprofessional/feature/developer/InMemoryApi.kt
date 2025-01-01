package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.features.user.UserData

interface InMemoryApi {
    fun loadKoinModules()
    fun unloadKoinModules()
    fun setUserData(userData: UserData)
    fun setProfessionals(pro: List<Professional>)
    fun resetProfessionals()
}
