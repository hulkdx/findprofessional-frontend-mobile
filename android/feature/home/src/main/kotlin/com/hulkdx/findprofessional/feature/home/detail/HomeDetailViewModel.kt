package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc


class HomeDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)
}
