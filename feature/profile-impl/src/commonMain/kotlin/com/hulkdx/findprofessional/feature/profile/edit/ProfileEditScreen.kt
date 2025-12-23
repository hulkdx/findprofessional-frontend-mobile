package com.hulkdx.findprofessional.feature.profile.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.firstName
import com.hulkdx.findprofessional.core.resources.lastName
import com.hulkdx.findprofessional.core.resources.save
import com.hulkdx.findprofessional.core.resources.skypeId
import com.hulkdx.findprofessional.core.ui.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.ui.commonui.CUTextField
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.utils.koinViewModel
import com.hulkdx.findprofessional.core.utils.singleClick
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileEditScreen(viewModel: ProfileEditViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()

    ProfileEditScreen(
        uiState = uiState,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        onFirstNameChange = viewModel::setFirstName,
        onLastNameChange = viewModel::setLastName,
        onSkypeIdChange = viewModel::setSkypeId,
        onSaveButtonClicked = viewModel::onSaveButtonClicked
    )
}

@Composable
fun ProfileEditScreen(
    uiState: User,
    error: String?,
    onErrorDismissed: () -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onSkypeIdChange: (String) -> Unit,
    onSaveButtonClicked: () -> Unit,
) {
    AppNavBarContainer(
        modifier = Modifier.testTag("ProfileEditScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = AppNavigationBarDimens.Height.dp)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            item {
                Item(
                    hint = stringResource(Res.string.firstName),
                    value = uiState.firstName,
                    onValueChanged = onFirstNameChange,
                )
            }
            item {
                Item(
                    hint = stringResource(Res.string.lastName),
                    value = uiState.lastName,
                    onValueChanged = onLastNameChange,
                )
            }
            item {
                Item(
                    hint = stringResource(Res.string.skypeId),
                    value = uiState.skypeId ?: "",
                    onValueChanged = onSkypeIdChange,
                )
            }

            item { SaveButton(onSaveButtonClicked) }
        }
    }
}

@Composable
private fun Item(
    hint: String,
    value: String,
    onValueChanged: (String) -> Unit,
    singleLine: Boolean = true,
) {
    CUTextField(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        value = value,
        onValueChanged = onValueChanged,
        label = { Text(hint) },
        singleLine = singleLine,
    )
}

@Composable
private fun SaveButton(
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = stringResource(Res.string.save),
        onClick = singleClick(onClick),
    )
}
