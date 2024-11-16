package com.hulkdx.findprofessional.feature.pro.availability

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.commonui.navbar.ProAppNavBarContainer

@Composable
fun ProAvailabilityScreen() {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProAvailabilityScreen"),
        error = null,
        onErrorDismissed = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TODO:
        }
    }
}
