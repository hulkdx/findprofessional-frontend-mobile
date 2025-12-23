package com.hulkdx.findprofessional.feature.booking.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.storage.getNormalUser
import com.hulkdx.findprofessional.core.utils.generateUuidV7
import com.hulkdx.findprofessional.feature.booking.summery.exception.SkypeIdNotFound

class CreateBookingUseCase(
    private val api: ProfessionalApi,
    private val userStorage: UserStorage,
) {

    suspend fun execute(
        amountInCents: Long,
        currency: String,
        availabilities: List<ProfessionalAvailability>,
        professional: Professional
    ) = runCatching {
        if (userStorage.getNormalUser()?.skypeId.isNullOrBlank()) {
            throw SkypeIdNotFound()
        }

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
