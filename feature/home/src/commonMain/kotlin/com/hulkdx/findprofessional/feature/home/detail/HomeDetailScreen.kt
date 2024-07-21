package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.icons.CUChatButton
import com.hulkdx.findprofessional.core.commonui.icons.CULikeButton
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookNow
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2SemiBold
import com.hulkdx.findprofessional.feature.home.detail.availability.Availability
import com.hulkdx.findprofessional.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.feature.home.detail.review.Review
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeScreenDimens.BOTTOM_PART_HEIGHT
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeScreenDimens.OUTER_HORIZONTAL_PADDING
import com.hulkdx.findprofessional.feature.home.main.view.Description
import com.hulkdx.findprofessional.feature.home.main.view.TopRow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun HomeDetailScreen(
    professional: Professional,
    viewModel: HomeDetailViewModel = koinViewModel { parametersOf(professional) },
) {
    val availability by viewModel.availability.collectAsState()

    HomeDetailScreen(
        professional,
        availability ?: return,
        viewModel::onShowMoreReviewClick,
        viewModel::onLikeClick,
        viewModel::onChatClick,
        viewModel::onBookClick,
        viewModel::availabilityMonthMinusOne,
        viewModel::availabilityMonthPlusOne,
    )
}

@Composable
fun HomeDetailScreen(
    professional: Professional,
    availability: AvailabilityData,
    onShowMoreReviewClick: () -> Unit,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onBookClicked: () -> Unit,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("HomeDetailScreen")
    ) {
        LazyColumn(
            modifier = Modifier
                .testTag("HomeDetailScreen.LazyColumn")
                .padding(bottom = BOTTOM_PART_HEIGHT.dp)
        ) {
            item { TopHeader(professional) }
            Availability(availability, availabilityMonthMinusOne, availabilityMonthPlusOne)
            Review(professional, onShowMoreReviewClick)
        }
        BottomPart(
            professional.isFav,
            onLikeClick,
            onChatClick,
            onBookClicked,
        )
    }
}

@Composable
private fun BoxScope.BottomPart(
    isFav: Boolean,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onBookClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(BOTTOM_PART_HEIGHT.dp)
            .align(Alignment.BottomStart)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CULikeButton(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(48.dp),
            isSelected = false,
            onClick = onLikeClick,
        )
        CUChatButton(
            modifier = Modifier
                .padding(start = 8.dp, end = 20.dp)
                .size(48.dp),
            onClick = onChatClick,
        )
        CUFilledButton(
            modifier = Modifier
                .weight(1F)
                .padding(end = 24.dp),
            text = stringResource(Res.string.bookNow),
            colors = buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            contentPadding = PaddingValues(0.dp),
            onClick = onBookClicked,
        )
    }
}

@Composable
private fun TopHeader(professional: Professional) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 49.dp)
            .padding(horizontal = OUTER_HORIZONTAL_PADDING.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(24.dp)
    ) {
        TopRow(professional)
        AboutMeText()
        Description(professional)
    }
}

@Composable
private fun AboutMeText() {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "About Me",
        style = body2SemiBold,
        color = MaterialTheme.colorScheme.errorContainer,
    )
}

@Preview
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
            Professional(
                1,
                "test@email.com",
                "Luba",
                "Mikaela",
                "Life coach",
                100,
                "EUR",
                "https://i.imgur.com/5Yma8Kl.jpeg",
                "5.0",
                "former professional boxer who competed from 1985 to 2005",
                availability = listOf(),
                reviewSize = "100",
                reviews = listOf(
                    ProfessionalReview(
                        id = 0,
                        user = User(
                            profileImage = "",
                            firstName = "Stefan",
                            lastName = "Holman",
                            email = "",
                        ),
                        rate = 4,
                        contentText = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\nfeeling like I’ve learned a lot.",
                        createdAt = Clock.System.now(),
                        updatedAt = Clock.System.now(),
                    )
                ),
            ),
            availability = AvailabilityData(
                "January 2022",
                5,
                31,
                LocalDate(2022, 1, 6),
                listOf(
                    LocalDate(2022, 1, 6),
                    LocalDate(2022, 1, 7),
                    LocalDate(2022, 1, 12),
                )
            ),
            {},
            {},
            {},
            {},
            {},
            {},
        )
    }
}
