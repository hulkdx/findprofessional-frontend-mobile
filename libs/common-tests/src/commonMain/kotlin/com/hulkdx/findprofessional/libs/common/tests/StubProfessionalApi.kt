package com.hulkdx.findprofessional.libs.common.tests

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.core.features.pro.model.response.GetBookingStatusResponse
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.Token
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData

open class StubProfessionalApi : ProfessionalApi {
    override suspend fun findAll(): List<Professional> {
        return emptyList()
    }

    override suspend fun register(request: SignUpProRequest): UserData {
        return UserData(
            token = Token(accessToken = "antiopam", refreshToken = "consul"),
            user = User()
        )
    }

    override suspend fun update(proUser: ProUser) {
    }

    override suspend fun getAvailability(): List<ProfessionalAvailability> {
        return emptyList()
    }

    override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
    }

    override suspend fun createBooking(request: CreateBookingRequest): CreateBookingResponse {
        return CreateBookingResponse(
            id = 5828,
            paymentIntent = "oporteat",
            customerSessionClientSecret = "lectus",
            customer = "dicit",
            publishableKey = "adversarium"
        )
    }

    override suspend fun getBookingStatus(id: Long): GetBookingStatusResponse {
        return GetBookingStatusResponse(status = GetBookingStatusResponse.Status.PENDING)
    }
}
