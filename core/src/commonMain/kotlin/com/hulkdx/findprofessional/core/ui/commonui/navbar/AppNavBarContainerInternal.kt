package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBarDurationDefault
import com.hulkdx.findprofessional.core.ui.utils.`if`

@Composable
internal fun AppNavBarContainerInternal(
    modifier: Modifier = Modifier,
    hasStatusBarPadding: Boolean = true,
    error: String?,
    items: List<NavData>,
    onErrorDismissed: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
    errorDurationMillis: Long? = CUSnackBarDurationDefault,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .`if`(hasStatusBarPadding) {
                statusBarsPadding()
            }
    ) {
        content()
        AppNavigationBarInternal(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
        )
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
            durationMillis = errorDurationMillis,
        )
    }
}
