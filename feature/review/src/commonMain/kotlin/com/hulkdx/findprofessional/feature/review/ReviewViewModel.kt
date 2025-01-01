package com.hulkdx.findprofessional.feature.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.features.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val professional: Professional,
    private val useCase: ReviewUseCase,
) : ViewModel() {

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()
    private val _reviews = MutableStateFlow(listOf<ProfessionalReview>())
    val reviews = _reviews.asStateFlow()

    init {
        loadReviews()
    }

    private fun loadReviews() = viewModelScope.launch {
        when (val result = useCase.findAll(professional.id)) {
            is ReviewUseCase.Result.DoNothing -> {}
            is ReviewUseCase.Result.Error -> setError(result.message)
            is ReviewUseCase.Result.Success -> setReviews(result.reviews)
        }
    }

    fun onLastItemVisible() {
        loadReviews()
    }

    fun setError(error: StringOrRes?) = _error.update { error }
    private fun setReviews(reviews: List<ProfessionalReview>) = _reviews.update { it + reviews }
}
