package com.hulkdx.findprofessional.feature.pro.schedule.usecase

import com.hulkdx.findprofessional.feature.pro.api.ProfessionalApi

class GetScheduleUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun execute() = runCatching {
        api.getProBookings()
    }
}
