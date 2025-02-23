package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class BookingSummeryViewModel(
    professional: Professional,
    times: SelectedTimes,
    useCase: BookingSummeryUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    val uiState = useCase.getUiState(professional, times)
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onCheckoutClicked() {
    }

    fun onEditSkypeIdClicked() {
        navigator.navigate(NavigationScreen.ProfileEdit)
    }

    fun setError(error: StringOrRes?) = _error.update { error }
}
