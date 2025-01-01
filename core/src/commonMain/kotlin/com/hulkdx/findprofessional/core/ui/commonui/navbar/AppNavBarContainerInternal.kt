package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBar

@Composable
internal fun AppNavBarContainerInternal(
    modifier: Modifier = Modifier,
    error: String?,
    items: List<NavData>,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .statusBarsPadding()
    ) {
        content()
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )
        AppNavigationBarInternal(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
        )
    }
}
