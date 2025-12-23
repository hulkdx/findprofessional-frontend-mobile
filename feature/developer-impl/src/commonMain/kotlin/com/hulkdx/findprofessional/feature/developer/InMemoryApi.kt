package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData

interface InMemoryApi {
    fun loadKoinModules()
    fun unloadKoinModules()
    fun setUserData(userData: UserData)
    fun setProfessionals(pro: List<Professional>)
    fun resetProfessionals()
    fun setProAvailability(availability: List<ProfessionalAvailability>)
}
