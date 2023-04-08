package com.hulkdx.findprofessional.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.theme.AppTheme


@Composable
fun HomeScreen() {
    Text(modifier = Modifier.testTag("HomeScreen"), text = "HomeScreen")
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}
