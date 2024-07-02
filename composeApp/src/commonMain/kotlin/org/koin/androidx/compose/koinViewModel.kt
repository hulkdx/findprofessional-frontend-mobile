package org.koin.androidx.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope
import org.koin.core.scope.Scope


// TODO: replace it when koin has koinViewModel to compose multiplatform
@Composable
inline fun <reified VM : ViewModel> koinViewModel(): VM {
    val scope = currentKoinScope()
    return viewModel { scope.get() }
}

@Composable
inline fun <reified T> koinInjectAll(
    scope: Scope = currentKoinScope(),
): List<T> {
    return remember(scope) { scope.getAll() }
}
