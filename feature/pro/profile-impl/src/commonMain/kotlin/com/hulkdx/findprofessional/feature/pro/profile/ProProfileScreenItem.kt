package com.hulkdx.findprofessional.feature.pro.profile

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.editProfile
import com.hulkdx.findprofessional.core.resources.logout
import com.hulkdx.findprofessional.core.resources.payoutSetup
import org.jetbrains.compose.resources.stringResource

data class ProProfileScreenItem(
    val title: String,
    val onClick: () -> Unit,
)

@Composable
fun proProfileScreenItems(
    viewModel: ProProfileViewModel,
) = listOf(
    ProProfileScreenItem(
        title = stringResource(Res.string.editProfile),
        onClick = viewModel::onEditProfileClicked,
    ),
    ProProfileScreenItem(
        title = stringResource(Res.string.payoutSetup),
        onClick = viewModel::onPayoutSetupClicked,
    ),
    ProProfileScreenItem(
        title = stringResource(Res.string.logout),
        onClick = viewModel::onLogoutClicked,
    )
)

@Composable
fun proProfileScreenItems() = listOf(
    ProProfileScreenItem(
        title = stringResource(Res.string.editProfile),
        onClick = {},
    ),
    ProProfileScreenItem(
        title = stringResource(Res.string.payoutSetup),
        onClick = {},
    ),
    ProProfileScreenItem(
        title = stringResource(Res.string.logout),
        onClick = {},
    )
)
