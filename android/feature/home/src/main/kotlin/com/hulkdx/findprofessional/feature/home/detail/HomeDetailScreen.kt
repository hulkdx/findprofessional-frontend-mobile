package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.navbar.AppNavBarContainer
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeDetailScreen(
    professional: Professional,
    viewModel: HomeDetailViewModel = getViewModel(),
) {
    AppNavBarContainer(testTag = "HomeScreenDetail", error = viewModel.error) {
        HomeDetailScreen(
            viewModel.professional,
            viewModel::onBookClick
        )
    }
}

@Composable
private fun HomeDetailScreen(
    professional: Professional,
    onBookClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // TODO: wait for the design
        Button(onClick = onBookClick) {
            Text(text = "Book")
        }
    }
}

@Preview
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
            Professional(
                id = 1,
                firstName = "Mike",
                lastName = "Tyson",
                coachType = "Life coach",
                priceNumber = 100,
                priceCurrency = "EUR",
                email = "",
                profileImageUrl = "https://imgur.com/gallery/7R6wmYb",
                rating = "5.0",
                description = "former professional boxer who competed from 1985 to 2005"
            )
        )
    }
}
