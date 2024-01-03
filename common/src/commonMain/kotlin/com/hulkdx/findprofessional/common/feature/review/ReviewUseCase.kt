package com.hulkdx.findprofessional.common.feature.review

import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.utils.generalError

class ReviewUseCase(
    private val api: ProfessionalApi,
) {
    suspend fun findAll(professionalId: Int) = try {
        api.findAllReviews(professionalId) to null
    } catch (e: Exception) {
        emptyList<ProfessionalReview>() to e.generalError()
    }
}
