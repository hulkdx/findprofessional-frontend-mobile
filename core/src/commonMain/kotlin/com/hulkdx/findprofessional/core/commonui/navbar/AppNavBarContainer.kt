package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.utils.StringOrRes
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppNavBarContainer(
    modifier: Modifier = Modifier,
    testTag: String,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .statusBarsPadding()
            .testTag(testTag)
    ) {
        content()
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )
        AppNavigationBar()
    }
}

@Composable
fun AppNavBarContainer(
    modifier: Modifier = Modifier,
    testTag: String,
    error: StringOrRes?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    AppNavBarContainer(
        modifier,
        testTag,
        error?.localized(),
        onErrorDismissed,
        content,
    )
}

@Preview
@Composable
private fun AppNavBarContainerPreview() {
    AppTheme {
        AppNavBarContainer(
            modifier = Modifier,
            testTag = "",
            error = null as String?,
            onErrorDismissed = {},
        ) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}
