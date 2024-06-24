package com.hulkdx.findprofessional.common.feature.review

import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.utils.StringOrRes
import com.hulkdx.findprofessional.common.utils.generalError

const val PAGE_SIZE = 10

class ReviewUseCase(
    private val api: ProfessionalApi,
) {
    private var isLoading = false
    private var page = 1

    suspend fun findAll(professionalId: Int) = try {
        if (isLoading) {
            Result.DoNothing
        } else {
            isLoading = true
            val res = api.findAllReviews(professionalId, page, PAGE_SIZE)
            if (res.isNotEmpty()) {
                page++
            }
            isLoading = false
            Result.Success(res)
        }
    } catch (e: Exception) {
        Result.Error(e.generalError())
    }

    sealed interface Result {
        data object DoNothing : Result
        data class Success(val reviews: List<ProfessionalReview>) : Result
        data class Error(val message: StringOrRes? = null) : Result
    }
}
