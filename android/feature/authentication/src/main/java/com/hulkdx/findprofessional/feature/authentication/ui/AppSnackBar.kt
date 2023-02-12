package com.hulkdx.findprofessional.feature.authentication.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AppSnackBar(
    modifier: Modifier = Modifier,
    message: String?,
) {
    if (message == null) {
        return
    }

    val hostState = remember { SnackbarHostState() }
    LaunchedEffect(message) {
        hostState.showSnackbar(message)
    }
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    )
}
