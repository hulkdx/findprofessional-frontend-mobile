package com.hulkdx.findprofessional.core.commonui.pagination

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

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

@Composable
fun LazyColumnWithLastItem(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    onLastItemVisible: () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    SetupOnLastItemVisible(state, onLastItemVisible)
    LazyColumn(
        modifier = modifier,
        state = state,
        content = content,
    )
}
