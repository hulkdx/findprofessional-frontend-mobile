package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ProfessionalUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _professionals = MutableStateFlow(listOf<Professional>())
    val professionals = _professionals.asStateFlow()
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    init {
        findAll()
    }

    private fun findAll() = viewModelScope.launch {
        val (professionals, err) = useCase.findAll()
        if (err != null) {
            setError(err)
        } else {
            _professionals.value = professionals
        }
    }

    fun onSearchClick(query: String) {
        // TODO:
    }

    fun onLikeClick(professional: Professional) {
        // TODO:
    }

    fun onItemClick(professional: Professional) {
        // TODO:
//        navigator.navigate(NavigationScreen.HomeDetail(professional))
    }

    fun setError(error: StringOrRes?) {
        _error.value = error
    }
}
