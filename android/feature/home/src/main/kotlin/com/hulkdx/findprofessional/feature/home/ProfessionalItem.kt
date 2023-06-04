package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.hulkdx.findprofessional.common.feature.home.Professional

@Composable
internal fun ProfessionalItem(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    Column(
        modifier = Modifier,
    ) {
        // TODO: move this into CU
        val imageLoader = LocalContext.current.imageLoader.newBuilder()
            .logger(DebugLogger())
            .build()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(professional.imageUrl)
//              TODO:
//                .placeholder()
//                .error()
                .build(),
            imageLoader = imageLoader,
            contentDescription = "",
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { onLikeClick(professional) }
        ) {
            Text("Like")
        }
        Text(text = professional.title)
        Text(text = professional.description)
        Text(text = professional.price)
    }
}

@Preview
@Composable
private fun ProfessionalItemPreview() {
    ProfessionalItem(
        professional = Professional(
            title = "Mike Tyson",
            description = "Boxer",
            price = "100$/h",
            imageUrl = "https://imgur.com/gallery/7R6wmYb"
        ),
        onLikeClick = {},
        onItemClick = {}
    )
}
