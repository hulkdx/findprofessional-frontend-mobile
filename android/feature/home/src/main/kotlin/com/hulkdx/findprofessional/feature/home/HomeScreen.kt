package com.hulkdx.findprofessional.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


@Composable
fun HomeScreen() {
    Text(modifier = Modifier.testTag("HomeScreen"), text = "HomeScreen")
}
