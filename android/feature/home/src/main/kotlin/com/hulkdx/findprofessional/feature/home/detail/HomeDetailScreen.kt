package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.AppNavBarContainer
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeDetailScreen(viewModel: HomeDetailViewModel = getViewModel()) {
    AppNavBarContainer(testTag = "HomeScreenDetail", error = viewModel.error) {
        HomeDetailScreen(
        )
    }
}

@Composable
private fun HomeDetailScreen(
) {
}

@Preview
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
        )
    }
}
