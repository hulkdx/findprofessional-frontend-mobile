package com.hulkdx.findprofessional.libs.common.tests

import com.hulkdx.findprofessional.feature.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.model.user.Token
import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData
import com.hulkdx.findprofessional.feature.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.feature.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.feature.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.feature.pro.model.response.GetBookingStatusResponse

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
