package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.HomeUseCase
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: HomeUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)
    val professionals = savedStateHandle.getStateFlow<List<Professional>>("professionals", listOf())

    init {
        findAll()
    }

    private fun findAll() = viewModelScope.launch {
        val (pro, err) = useCase.findAll()
        if (err != null) {
            setError(err)
        } else {
            setProfessionals(pro)
        }
    }

    fun onSearchClick(query: String) {
    }

    fun onLikeClick(professional: Professional) {
    }

    fun onItemClick(professional: Professional) {
        navigator.navigate(NavigationScreen.HomeDetail(professional))
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    private fun setProfessionals(pro: List<Professional>) {
        savedStateHandle["professionals"] = pro
    }
}
