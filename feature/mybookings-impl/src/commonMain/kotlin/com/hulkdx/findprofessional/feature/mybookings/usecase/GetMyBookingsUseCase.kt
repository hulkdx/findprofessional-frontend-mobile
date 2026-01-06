package com.hulkdx.findprofessional.feature.mybookings.usecase

import com.hulkdx.findprofessional.feature.pro.api.ProfessionalApi


class GetMyBookingsUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun execute() = runCatching {
        api.getUserBookings()
    }
}
