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
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlin.time.Duration.Companion.minutes

class UpdateAvailabilityUseCase(
    private val professionalApi: ProfessionalApi,
    private val storage: AvailabilityStorage,
) {
    suspend fun execute(timeSlots: List<TimeSlot>, date: LocalDate, timeZone: TimeZone): StringOrRes? {
        return try {
            if (isNotValid(timeSlots)) {
                return Res.string.invalidTime.toStringOrRes()
            }
            val request = UpdateAvailabilityRequest(createItems(timeSlots, date, timeZone))
            professionalApi.updateAvailability(request)
            val new = professionalApi.getAvailability()
            storage.set(new)
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

    private fun createItems(
        timeSlots: List<TimeSlot>,
        date: LocalDate,
        timeZone: TimeZone,
    ): List<UpdateAvailabilityItemRequest> {
        val set = mutableSetOf<Instant>()
        val result = mutableListOf<UpdateAvailabilityItemRequest>()
        for (t in timeSlots) {
            var current = date.atTime(t.from).toInstant(timeZone)
            val slotEnd = date.atTime(t.to).toInstant(timeZone)
            while (current < slotEnd) {
                val currentEnd = current.plus(30.minutes)
                if (!set.contains(current)) {
                    result.add(
                        UpdateAvailabilityItemRequest(
                            from = current,
                            to = currentEnd,
                        )
                    )
                    set.add(current)
                }
                current = currentEnd
            }
        }
        return result
    }
}
