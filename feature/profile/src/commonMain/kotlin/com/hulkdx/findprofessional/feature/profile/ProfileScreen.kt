package com.hulkdx.findprofessional.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.becomeCoach
import com.hulkdx.findprofessional.core.resources.logout
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.h1Medium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsState()

    ProfileScreen(
        onBecomeCoachClicked = viewModel::onBecomeCoachClicked,
        onLogoutClicked = viewModel::onLogoutClicked,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProfileScreen(
    onLogoutClicked: () -> Unit,
    onBecomeCoachClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    AppNavBarContainer(
        testTag = "ProfileScreen",
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = AppNavigationBarDimens.Height.dp)
        ) {
            item { Header() }
            item {
                ProfileItem(
                    text = stringResource(Res.string.becomeCoach),
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = onBecomeCoachClicked,
                )
                ProfileItem(
                    text = stringResource(Res.string.logout),
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = onLogoutClicked,
                )
            }
        }
    }
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp, bottom = 32.dp),
        style = h1Medium,
        textAlign = TextAlign.Center,
        text = stringResource(Res.string.profile),
    )
}

@Composable
private fun ProfileItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onClick)
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier,
            text = text,
            style = body1,
        )
        Icon(
            imageVector = icon,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            onBecomeCoachClicked = {},
            onLogoutClicked = {},
            error = "",
            onErrorDismissed = {},
        )
    }
}
