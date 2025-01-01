package com.hulkdx.findprofessional.core.ui.commonui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AccessibilityManager
import androidx.compose.ui.platform.LocalAccessibilityManager
import kotlinx.coroutines.delay

@Composable
fun CUSnackBar(
    modifier: Modifier = Modifier,
    message: String?,
    onDismiss: () -> Unit,
) {
    if (message == null) {
        return
    }

    val hostState = remember { SnackbarHostState() }
    val delayDuration = delayDuration(hostState)
    LaunchedEffect(message) {
        hostState.showSnackbar(message)
        delay(delayDuration)
        onDismiss()
    }
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    )
}

// copy pasted from the source code
@Composable
private fun delayDuration(hostState: SnackbarHostState): Long {
    val currentSnackbarData = hostState.currentSnackbarData
    val accessibilityManager = LocalAccessibilityManager.current

    if (currentSnackbarData == null) {
        return 0
    }
    return currentSnackbarData.visuals.duration.toMillis(
        currentSnackbarData.visuals.actionLabel != null,
        accessibilityManager,
    )
}

// copy pasted from the source code
private fun SnackbarDuration.toMillis(
    hasAction: Boolean,
    accessibilityManager: AccessibilityManager?,
): Long {
    val original = when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
    if (accessibilityManager == null) {
        return original
    }
    return accessibilityManager.calculateRecommendedTimeoutMillis(
        original,
        containsIcons = true,
        containsText = true,
        containsControls = hasAction,
    )
}
