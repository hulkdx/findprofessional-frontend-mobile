package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.response.GetBookingStatusResponse.Status.PENDING
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

private val TIMEOUT = 2.minutes
private val DELAY = 2.seconds

class CheckBookingStatusUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun execute(bookingId: Long) = runCatching {
        var status = PENDING
        withTimeout(TIMEOUT) {
            while (coroutineContext.isActive && status == PENDING) {
                delay(DELAY)
                runCatching { api.getBookingStatus(bookingId).status }
                    .onSuccess {
                        status = it
                    }
            }
        }
        return@runCatching status
    }
}
