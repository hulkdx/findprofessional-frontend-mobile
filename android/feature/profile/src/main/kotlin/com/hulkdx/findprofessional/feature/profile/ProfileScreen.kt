package com.hulkdx.findprofessional.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.AppNavigationBar
import com.hulkdx.findprofessional.feature.navigation.StubNavigator
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.getViewModel
import org.koin.compose.koinInject


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = getViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()

    ProfileScreen(
        navigator = koinInject(),
        error = error?.localized(),
        onErrorDismissed = { viewModel.error.set(null) },
    )
}

@Composable
private fun ProfileScreen(
    navigator: Navigator,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onTertiary)
            .fillMaxSize()
            .systemBarsPadding()
            .testTag("ProfileScreen")
    ) {
        Column {
            ProfileItem(
                text = "Logout",
                icon = R.drawable.ic_like,
                onClick = {}
            )
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )
        AppNavigationBar(navigator)
    }
}

@Composable
private fun ProfileItem(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
        )
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            navigator = StubNavigator(),
            error = "",
            onErrorDismissed = {},
        )
    }
}
