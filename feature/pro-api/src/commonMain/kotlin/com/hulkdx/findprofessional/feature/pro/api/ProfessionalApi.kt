package com.hulkdx.findprofessional.feature.pro.api

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.feature.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.feature.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.feature.pro.model.response.GetBookingStatusResponse
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
