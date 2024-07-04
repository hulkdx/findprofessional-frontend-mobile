package com.hulkdx.findprofessional.core.commonui.pagination

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun rememberIsLastItemVisible(lazyListState: LazyListState) =
    remember { derivedStateOf { lazyListState.isLastItemVisible } }

@Composable
fun SetupOnLastItemVisible(lazyListState: LazyListState, onLastItemVisible: () -> Unit) {
    val isLastItemVisible by rememberIsLastItemVisible(lazyListState)
    LaunchedEffect(isLastItemVisible) {
        if (isLastItemVisible) {
            onLastItemVisible()
        }
    }
}
