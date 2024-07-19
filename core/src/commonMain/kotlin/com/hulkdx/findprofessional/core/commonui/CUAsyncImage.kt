package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.util.DebugLogger
import com.hulkdx.findprofessional.core.config.isDebug

@Composable
fun CUAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
) {
    val imageLoader = if (isDebug()) {
        ImageLoader
            .Builder(LocalPlatformContext.current)
            .logger(DebugLogger())
            .build()
    } else {
        ImageLoader(LocalPlatformContext.current)
    }

    val imageRequest = ImageRequest.Builder(LocalPlatformContext.current)
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
