package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.hulkdx.findprofessional.core.BuildConfig

@Composable
fun CUAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    val imageLoader = if (BuildConfig.DEBUG) {
        LocalContext.current.imageLoader.newBuilder()
            .logger(DebugLogger())
            .build()
    } else {
        LocalContext.current.imageLoader
    }

    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                    )
            )
        },
    )
}
