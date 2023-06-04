package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.Professional

@Composable
internal fun ProfessionalItem(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
// TODO: use coil to get image from url
//        Image(
//            painter = painterResource(R.drawable.professional_image),
//            contentDescription = "Professional Image",
//            modifier = Modifier.size(120.dp)
//        )
        Button(
            onClick = { onLikeClick(professional) },
            modifier = Modifier.padding(top = 8.dp)
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
