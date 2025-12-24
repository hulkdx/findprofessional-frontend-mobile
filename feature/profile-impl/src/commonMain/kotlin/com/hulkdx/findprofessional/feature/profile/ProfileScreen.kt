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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.becomeCoach
import com.hulkdx.findprofessional.core.resources.contactSupport
import com.hulkdx.findprofessional.core.resources.editProfile
import com.hulkdx.findprofessional.core.resources.logout
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.resources.reportProblem
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body1
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsState()

    ProfileScreen(
        onBecomeCoachClicked = viewModel::onBecomeCoachClicked,
        onLogoutClicked = viewModel::onLogoutClicked,
        onEditProfileClicked = viewModel::onEditProfileClicked,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProfileScreen(
    onLogoutClicked: () -> Unit,
    onBecomeCoachClicked: () -> Unit,
    onEditProfileClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    AppNavBarContainer(
        modifier = Modifier.testTag("ProfileScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = AppNavigationBarDimens.Height.dp)
        ) {
            item { Header() }
            item { ProfileItem(Res.string.editProfile, onEditProfileClicked) }
            item { ProfileItem(Res.string.becomeCoach, onBecomeCoachClicked) }
            item { ProfileItem(Res.string.logout, onLogoutClicked) }
            item {
                ProfileItem(Res.string.contactSupport, {
                    uriHandler.openUri("mailto:findprofessionalhelp@gmail.com?subject=Find%20Professional%20Support")
                })
            }
            item {
                ProfileItem(Res.string.reportProblem, {
                    uriHandler.openUri("https://docs.google.com/forms/d/e/1FAIpQLSeqJqZ51P7g-qhwKAWSQNHLs6k0G6IQ6nTGBlP5Kt6_d_0vzA/viewform?usp=publish-editor")
                })
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
    textRes: StringResource,
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
            modifier = Modifier.align(CenterVertically),
            text = stringResource(textRes),
            style = body1,
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
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
            onEditProfileClicked = {},
            error = "",
            onErrorDismissed = {},
        )
    }
}
