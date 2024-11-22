package com.hulkdx.findprofessional.feature.pro.profile.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProProfileScreen(viewModel: EditProProfileViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

}
