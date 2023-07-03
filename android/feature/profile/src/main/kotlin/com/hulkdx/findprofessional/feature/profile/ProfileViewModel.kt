package com.hulkdx.findprofessional.feature.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.logout.LogoutUseCase
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch


class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)

    fun onLogoutClicked() = viewModelScope.launch {
        logoutUseCase.logout()
    }
}
