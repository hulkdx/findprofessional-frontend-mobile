package com.hulkdx.findprofessional.feature.pro.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.ui.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.core.ui.theme.body1Medium
import com.hulkdx.findprofessional.core.ui.theme.body2
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProProfileScreen(viewModel: ProProfileViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    ProProfileScreen(
        name = uiState.name,
        profileImageUrl = uiState.profileImageUrl,
        items = proProfileScreenItems(viewModel),
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProProfileScreen(
    name: String,
    profileImageUrl: String,
    items: List<ProProfileScreenItem> = proProfileScreenItems(),
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProProfileScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        ProProfileScreenContent(name, profileImageUrl, items)
    }
}

@Composable
private fun ProProfileScreenContent(
    name: String,
    profileImageUrl: String,
    items: List<ProProfileScreenItem>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
    ) {
        ProProfileImage(profileImageUrl)
        ProProfileName(name)

        items.forEach {
            ProProfileItem(
                text = it.title,
                onClick = it.onClick,
            )
        }
    }
}

@Composable
internal fun ProProfileImage(url: String?) {
    val modifier = Modifier
        .padding(top = 53.dp)
        .size(75.dp)
        .clip(shape = CircleShape)

    if (url.isNullOrBlank()) {
        Spacer(modifier = modifier)
    } else {
        CUAsyncImage(
            modifier = modifier,
            url = url,
        )
    }
}

@Composable
internal fun ProProfileName(name: String) {
    Text(
        modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
        text = name,
        style = body1Medium,
        color = MaterialTheme.colorScheme.errorContainer,
    )
}

@Composable
private fun ProProfileItem(
    text: String,
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
            text = text,
            style = body2,
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
        )
    }
}
