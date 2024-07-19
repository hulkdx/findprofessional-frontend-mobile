package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.home.model.Professional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    val professionals = MutableStateFlow(listOf<Professional>())
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onSearchClick(query: String) {
    }

    fun onLikeClick(professional: Professional) {
    }

    fun onItemClick(professional: Professional) {
    }

    fun setError(error: StringOrRes?) {
        _error.value = error
    }
}
