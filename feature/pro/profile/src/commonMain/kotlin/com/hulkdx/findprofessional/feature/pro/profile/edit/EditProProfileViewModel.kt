package com.hulkdx.findprofessional.feature.pro.profile.edit

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.model.user.ProUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProProfileViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ProUser())
    val uiState = _uiState.asStateFlow()
}
