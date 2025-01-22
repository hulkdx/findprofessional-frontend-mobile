package com.hulkdx.findprofessional.feature.pro.availability.detail.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityItemRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.invalidTime
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.availability.detail.TimeSlot
import kotlinx.datetime.LocalDate

class UpdateAvailabilityUseCase(
    private val professionalApi: ProfessionalApi,
) {
    suspend fun execute(timeSlots: List<TimeSlot>, date: LocalDate): StringOrRes? {
        return try {
            if (isNotValid(timeSlots)) {
                return Res.string.invalidTime.toStringOrRes()
            }
            val request = UpdateAvailabilityRequest(
                timeSlots.map {
                    UpdateAvailabilityItemRequest(
                        date = date.toString(),
                        from = "${it.from}:00",
                        to = "${it.to}:00",
                    )
                }
            )
            professionalApi.updateAvailability(request)
            null
        } catch (e: Exception) {
            e.generalError()
        }
    }

    private fun isNotValid(timeSlots: List<TimeSlot>): Boolean {
        for (timeSlot in timeSlots) {
            if (timeSlot.from >= timeSlot.to) {
                return true
            }
        }
        return false
    }
}
