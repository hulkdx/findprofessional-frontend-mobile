package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.utils.localized
import com.hulkdx.findprofessional.core.commonui.CUSearchField
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.feature.navigation.navbar.AppNavigationBarDimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val professionals by viewModel.professionals.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    HomeScreen(
        professionals,
        error?.localized(),
        { viewModel.setError(null) },
        viewModel::onLikeClick,
        viewModel::onItemClick,
        viewModel::onSearchClick,
    )
}

@Composable
fun HomeScreen(
    professionals: List<Professional>,
    error: String?,
    onErrorDismissed: () -> Unit,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
    onSearchClick: (String) -> Unit,
) {
    AppNavBarContainer(
        testTag = "HomeScreen",
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        HomeScreen(
            professionals = professionals,
            onSearchClick = onSearchClick,
            onLikeClick = onLikeClick,
            onItemClick = onItemClick,
        )
    }
}

@Composable
private fun HomeScreen(
    professionals: List<Professional>,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
    onSearchClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Search(onSearchClick)
        HomeScreenItemList(
            professionals = professionals,
            onLikeClick = onLikeClick,
            onItemClick = onItemClick,
        )
    }
}

@Composable
private fun Search(onSearch: (String) -> Unit) {
    CUSearchField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        onSearch = onSearch,
    )
}

@Composable
private fun ColumnScope.HomeScreenItemList(
    professionals: List<Professional>,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = AppNavigationBarDimens.Height.dp)
            .weight(1F, true),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item { Spacer(Modifier.height(16.dp)) }
        items(
            professionals,
            key = { it.id },
        ) { professional ->
            HomeScreenItem(
                professional = professional,
                onLikeClick = onLikeClick,
                onItemClick = onItemClick,
            )
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            professionals = listOf(
                Professional(
                    id = 1,
                    firstName = "Mike",
                    lastName = "Tyson",
                    coachType = "Life coach",
                    priceNumber = 100,
                    priceCurrency = "EUR",
                    email = "",
                    reviewSize = "100",
                    profileImageUrl = "https://imgur.com/gallery/7R6wmYb",
                )
            ),
            error = null,
            onErrorDismissed = {},
            onSearchClick = {},
            onLikeClick = {},
            onItemClick = {},
        )
    }
}
