package com.hulkdx.findprofessional.feature.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.logout.LogoutUseCase
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun onLogoutClicked() = viewModelScope.launch {
        logoutUseCase.logout()
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
