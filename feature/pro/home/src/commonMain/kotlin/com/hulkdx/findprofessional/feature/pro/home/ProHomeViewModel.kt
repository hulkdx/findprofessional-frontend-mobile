package com.hulkdx.findprofessional.feature.pro.home

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProHomeViewModel(
) : ViewModel() {
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun setError(error: StringOrRes?) {
        _error.value = error
    }
}
