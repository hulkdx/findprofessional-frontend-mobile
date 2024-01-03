package com.hulkdx.findprofessional.feature.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.feature.review.ReviewUseCase
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.review.ReviewNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch


class ReviewViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ReviewUseCase,
) : ViewModel() {

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)
    val reviews = savedStateHandle.getStateFlow<List<ProfessionalReview>>("reviews", listOf())

    init {
        loadReviews()
    }

    private fun loadReviews() = viewModelScope.launch {
        val (reviews, err) = useCase.findAll(professional.value.id)
        if (err != null) {
            setError(err)
        } else {
            setReviews(reviews)
        }
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    private fun setReviews(reviews: List<ProfessionalReview>) {
        savedStateHandle["reviews"] = reviews
    }
}
