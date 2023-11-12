package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.hulkdx.findprofessional.core.utils.isDebug

@Composable
fun CUAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
) {
    val imageLoader = if (isDebug()) {
        LocalContext.current.imageLoader.newBuilder()
            .logger(DebugLogger())
            .build()
    } else {
        LocalContext.current.imageLoader
    }

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .build()

    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        contentScale = contentScale,
        loading = {
            AnimatedShimmer(Modifier.fillMaxSize())
        },
    )
}
