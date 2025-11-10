package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest

class CreateBookingUseCase(
    private val api: ProfessionalApi,
) {

    suspend fun execute(request: CreateBookingRequest, proId: String) = runCatching {
        api.createBooking(request, proId)
    }
}
