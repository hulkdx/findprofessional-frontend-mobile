package com.hulkdx.findprofessional.feature.mybookings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingHelp
import com.hulkdx.findprofessional.core.resources.myBookingsTitle
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyBookingsScreen(
    viewModel: MyBookingsViewModel = koinViewModel(),
) {
    MyBookingsScreen(null, {})
}

@Composable
fun MyBookingsScreen(
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    AppNavBarContainer(
        modifier = Modifier.testTag("MyBookingsScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        MyBookingsScreenContent()
    }
}

@Composable
fun MyBookingsScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState())
            .padding(bottom = AppNavigationBarDimens.Height.dp)
    ) {
        Header()
    }
}


@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            style = h1Medium,
            textAlign = TextAlign.Start,
            text = stringResource(Res.string.myBookingsTitle),
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpHeader()
    }
}

@Composable
private fun HelpHeader() {
    var showHelpDialog by remember { mutableStateOf(false) }
    IconButton(
        modifier = Modifier,
        onClick = { showHelpDialog = true },
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
            contentDescription = stringResource(Res.string.bookingHelp),
        )
    }
    if (showHelpDialog) {
        BookingHelpDialog(onDismiss = {
            @Suppress("AssignedValueIsNeverRead")
            showHelpDialog = false
        })
    }
}

@Preview
@Composable
private fun MyBookingsScreenPreview() {
    AppTheme {
        MyBookingsScreen(
            error = null,
            onErrorDismissed = {},
        )
    }
}
