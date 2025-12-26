package com.hulkdx.findprofessional.feature.mybookings.api

interface MyBookingApi {
    suspend fun getMyBookingsList(): MyBookingsResponse
}
