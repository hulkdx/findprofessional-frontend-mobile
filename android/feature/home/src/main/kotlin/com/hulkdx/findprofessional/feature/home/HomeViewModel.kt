package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc

class HomeViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)
    val professionals by savedStateHandle.getStateFlowWrapper<List<Professional>>(listOf())

    init {
        // TODO: test data / remove it
        professionals.set(listOf(
            Professional(
                title = "Mike Tyson",
                description = "Boxer",
                price = "100$/h",
                imageUrl = "https://i.imgur.com/HDgjt8R.jpeg"
            )
        ))
    }

    fun onSearchClick(query: String) {
    }

    fun onLikeClick(professional: Professional) {
    }

    fun onItemClick(professional: Professional) {
    }
}
