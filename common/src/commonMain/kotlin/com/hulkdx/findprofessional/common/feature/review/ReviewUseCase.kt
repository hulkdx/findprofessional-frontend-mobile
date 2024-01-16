package com.hulkdx.findprofessional.common.feature.review

import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.utils.generalError
import dev.icerock.moko.resources.desc.StringDesc

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
            val res = Result.Success(api.findAllReviews(professionalId, page, PAGE_SIZE))
            page++
            isLoading = false
            res
        }
    } catch (e: Exception) {
        Result.Error(e.generalError())
    }

    sealed interface Result {
        data object DoNothing : Result
        data class Success(val reviews: List<ProfessionalReview>) : Result
        data class Error(val message: StringDesc? = null) : Result
    }
}
