package com.hulkdx.findprofessional.feature.mybookings.api

interface MyBookingApi {
    suspend fun getUserBookings(): MyBookingsResponse
    suspend fun getProBookings(): MyBookingsResponse
}
