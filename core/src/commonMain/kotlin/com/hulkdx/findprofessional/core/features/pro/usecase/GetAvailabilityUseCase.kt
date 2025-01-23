package com.hulkdx.findprofessional.core.features.pro.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage
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
