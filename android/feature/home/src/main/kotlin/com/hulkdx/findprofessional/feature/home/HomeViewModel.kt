package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.HomeUseCase
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class HomeViewModel(
    savedStateHandle: SavedStateHandle,
    private val useCase: HomeUseCase,
) : ViewModel() {
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)
    val professionals by savedStateHandle.getStateFlowWrapper<List<Professional>>(listOf())

    init {
        findAll()
    }

    private fun findAll() = viewModelScope.launch {
        val (pro, err) = useCase.findAll()
        if (err != null) {
            error.set(err)
        } else {
            professionals.set(pro)
        }
    }

    fun onSearchClick(query: String) {
    }

    fun onLikeClick(professional: Professional) {
    }

    fun onItemClick(professional: Professional) {
    }
}
