package com.hulkdx.findprofessional.feature.pro.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer

@Composable
fun PayoutSetupScreen() {
    ProAppNavBarContainer(
        modifier = Modifier
            .fillMaxSize()
            .testTag("PayoutSetupScreen"),
        error = null,
        onErrorDismissed = {},
    ) {
    }
}
