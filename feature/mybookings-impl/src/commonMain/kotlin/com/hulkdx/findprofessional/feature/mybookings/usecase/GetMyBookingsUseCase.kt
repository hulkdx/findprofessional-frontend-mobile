package com.hulkdx.findprofessional.feature.mybookings.usecase

import com.hulkdx.findprofessional.feature.mybookings.api.MyBookingApi


class GetMyBookingsUseCase(
    private val api: MyBookingApi,
) {
    suspend fun execute() = runCatching {
        api.getMyBookingsList()
    }
}
