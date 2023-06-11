package com.hulkdx.findprofessional.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc

val TEST_PRO = Professional(
    title = "Mike Tyson",
    type = "Life coach",
    price = "100",
    currencyPrice = "EUR",
    star = "5.0",
    imageUrl = "https://i.imgur.com/HDgjt8R.jpeg",
)

class HomeViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)
    val professionals by savedStateHandle.getStateFlowWrapper<List<Professional>>(listOf())

    init {
        // TODO: test data / remove it
        professionals.set(listOf(TEST_PRO,TEST_PRO,TEST_PRO,TEST_PRO,TEST_PRO,TEST_PRO,TEST_PRO,TEST_PRO))
    }

    fun onSearchClick(query: String) {
    }

    fun onLikeClick(professional: Professional) {
    }

    fun onItemClick(professional: Professional) {
    }
}
