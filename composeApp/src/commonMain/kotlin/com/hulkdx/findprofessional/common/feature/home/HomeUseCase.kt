package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.utils.generalError

class HomeUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun findAll() = try {
        val response = api.findAll()
        response to null
    } catch (e: Exception) {
        emptyList<Professional>() to e.generalError()
    }
}
