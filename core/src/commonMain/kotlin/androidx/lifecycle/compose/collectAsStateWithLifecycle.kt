package androidx.lifecycle.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow


// TODO:?
@Composable
fun <T> StateFlow<T>.collectAsStateWithLifecycle() = collectAsState()
