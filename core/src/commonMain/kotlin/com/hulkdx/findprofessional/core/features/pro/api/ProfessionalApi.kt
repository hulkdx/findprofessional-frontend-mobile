package com.hulkdx.findprofessional.core.features.pro.api

import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.features.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.UserData

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
    suspend fun register(request: SignUpProRequest): UserData
    suspend fun update(proUser: ProUser)
}
