package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CreateBookingUseCase(
    private val api: ProfessionalApi,
) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun execute(request: CreateBookingRequest) = runCatching {
        val idempotencyKey = Uuid.random().toString()
        api.createBooking(request, idempotencyKey)
    }
}
