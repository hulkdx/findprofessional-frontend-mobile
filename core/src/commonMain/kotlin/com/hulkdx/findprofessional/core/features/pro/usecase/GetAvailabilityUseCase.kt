package com.hulkdx.findprofessional.core.features.pro.usecase

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage

class GetAvailabilityUseCase(
    private val api: ProfessionalApi,
    private val storage: AvailabilityStorage,
) {
    private var fetched = false

    suspend fun execute(): List<ProfessionalAvailability> {
        return if (!fetched) {
            fetched = true
            getFromApi()
        } else {
            getFromStorage()
        }
    }

    private suspend fun getFromApi(): List<ProfessionalAvailability> {
        val result = api.getAvailability()
        storage.set(result)
        return result
    }

    private suspend fun getFromStorage(): List<ProfessionalAvailability> {
        return storage.get() ?: getFromApi()
    }
}
