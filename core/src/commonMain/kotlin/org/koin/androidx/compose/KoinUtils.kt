package org.koin.androidx.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope


// TODO: replace it when koin has koinViewModel to compose multiplatform
@Composable
inline fun <reified VM : ViewModel> koinViewModel(): VM {
    val scope = currentKoinScope()
    return viewModel { scope.get() }
}
