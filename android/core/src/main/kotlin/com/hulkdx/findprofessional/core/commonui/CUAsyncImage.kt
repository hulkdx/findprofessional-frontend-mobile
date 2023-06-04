package com.hulkdx.findprofessional.core.commonui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun CUAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    // TODO: placeholder from
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
//              TODO:
//                .placeholder()
//                .error()
            .build(),
        contentDescription = contentDescription,
    )
}
