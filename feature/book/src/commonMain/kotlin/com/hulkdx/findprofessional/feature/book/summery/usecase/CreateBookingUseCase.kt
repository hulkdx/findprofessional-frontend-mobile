package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.utils.generateUuidV7

class CreateBookingUseCase(
    private val api: ProfessionalApi,
) {

    suspend fun execute(
        amountInCents: Long,
        currency: String,
        availabilities: List<ProfessionalAvailability>,
        professional: Professional
    ) = runCatching {
        val request = CreateBookingRequest(
            amountInCents = amountInCents,
            currency = currency,
            availabilities = availabilities.map { it.id.toString() },
            idempotencyKey = generateUuidV7(),
            proId = professional.id,
        )
        api.createBooking(request)
    }
}
