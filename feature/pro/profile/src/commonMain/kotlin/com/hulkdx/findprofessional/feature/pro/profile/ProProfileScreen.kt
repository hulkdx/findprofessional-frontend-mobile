package com.hulkdx.findprofessional.feature.pro.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.commonui.navbar.ProAppNavBarContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProProfileScreen() {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProProfileScreen"),
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
