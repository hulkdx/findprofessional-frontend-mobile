package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.home.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.home.model.Professional


class ProfessionalUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun findAll() = try {
        val response = api.findAll()
        response to null
    } catch (e: Exception) {
        emptyList<Professional>() to e.generalError()
    }
}
