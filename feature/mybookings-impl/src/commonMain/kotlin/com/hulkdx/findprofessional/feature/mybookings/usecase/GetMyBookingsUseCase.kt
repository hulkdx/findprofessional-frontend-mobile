package com.hulkdx.findprofessional.feature.mybookings.usecase

import com.hulkdx.findprofessional.feature.pro.model.request.MyBookingsRequest
import com.hulkdx.findprofessional.feature.pro.model.response.MyBookingsResponse

class GetMyBookingsUseCase(
) {
    suspend fun execute(request: MyBookingsRequest): MyBookingsResponse {
    }
}
