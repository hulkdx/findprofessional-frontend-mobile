package com.hulkdx.findprofessional.feature.pro.usecase

import com.hulkdx.findprofessional.feature.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.pro.storage.AvailabilityStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

class GetAvailabilityUseCase(
    private val api: ProfessionalApi,
    private val storage: AvailabilityStorage,
) {
    private var isFetched = false

    fun execute(): Flow<List<ProfessionalAvailability>> {
        return storage.getFlow()
            .onStart {
                if (!isFetched) {
                    isFetched = true
                    storage.set(api.getAvailability())
                }
            }
    }
}
