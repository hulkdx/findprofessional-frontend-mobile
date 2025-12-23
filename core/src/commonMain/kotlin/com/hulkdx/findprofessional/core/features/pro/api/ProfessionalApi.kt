package com.hulkdx.findprofessional.core.features.pro.api

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.core.features.pro.model.response.GetBookingStatusResponse
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
    suspend fun register(request: SignUpProRequest): UserData
    suspend fun update(proUser: ProUser)
    suspend fun getAvailability(): List<ProfessionalAvailability>
    suspend fun updateAvailability(request: UpdateAvailabilityRequest)
    suspend fun createBooking(request: CreateBookingRequest): CreateBookingResponse
    suspend fun getBookingStatus(id: Long): GetBookingStatusResponse
}
