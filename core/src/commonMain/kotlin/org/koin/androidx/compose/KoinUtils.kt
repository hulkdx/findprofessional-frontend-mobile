package org.koin.androidx.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope
import org.koin.core.parameter.ParametersDefinition


// TODO: replace it when koin has koinViewModel to compose multiplatform
@Composable
inline fun <reified T : ViewModel> koinViewModel(noinline parameters: ParametersDefinition? = null): T {
    val scope = currentKoinScope()
    return viewModel { scope.get<T>(parameters = parameters) }
}
