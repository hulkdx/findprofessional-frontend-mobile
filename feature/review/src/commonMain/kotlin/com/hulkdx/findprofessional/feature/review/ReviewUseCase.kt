package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError

const val PAGE_SIZE = 10

class ReviewUseCase(
    private val api: ReviewApi,
) {
    private var isLoading = false
    private var page = 1

    suspend fun findAll(professionalId: Int) = try {
        if (isLoading) {
            Result.DoNothing
        } else {
            isLoading = true
            val res = api.findAll(professionalId, page, PAGE_SIZE)
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
