package com.hulkdx.findprofessional.feature.developer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.theme.AppTheme


@Composable
fun DeveloperScreen() {
    // TODO: update data
    DeveloperScreen(
        checked = true,
        onCheckedChange = {},
    )
}

@Composable
fun DeveloperScreen(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
) {
    Column(
        modifier = Modifier
            .testTag("DeveloperScreen")
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
            Text(text = "Use Mock Data")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        DeveloperScreen(
            checked = true,
            onCheckedChange = {},
        )
    }
}

