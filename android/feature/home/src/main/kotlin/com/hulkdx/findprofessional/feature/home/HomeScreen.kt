package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.core.commonui.CUSearchField
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.h1
import com.hulkdx.findprofessional.feature.navigation.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.resources.MR
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    val professionals by viewModel.professionals.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    AppNavBarContainer(
        testTag = "HomeScreen",
        error = error,
        onErrorDismissed = { viewModel.setError(null) },
    ) {
        HomeScreen(
            professionals = professionals,
            onSearchClick = viewModel::onSearchClick,
            onLikeClick = viewModel::onLikeClick,
            onItemClick = viewModel::onItemClick,
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
        Search(onSearchClick)
        Title()
        ProfessionalList(
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
private fun Title() {
    Text(
        modifier = Modifier.padding(top = 24.dp, start = 24.dp),
        text = stringResource(id = MR.strings.professionals.resourceId),
        style = h1,
    )
}

@Composable
private fun ColumnScope.ProfessionalList(
    professionals: List<Professional>,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .weight(1F, true),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            professionals,
            key = { it.id },
        ) { professional ->
            ProfessionalItem(
                professional = professional,
                onLikeClick = onLikeClick,
                onItemClick = onItemClick,
            )
        }
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
                    profileImageUrl = "https://imgur.com/gallery/7R6wmYb",
                    availability = listOf(),
                    reviews = ProfessionalReview(100, listOf()),
                )
            ),
            onSearchClick = {},
            onLikeClick = {},
            onItemClick = {},
        )
    }
}
