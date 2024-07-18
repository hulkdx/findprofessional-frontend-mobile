package com.hulkdx.findprofessional.core.commonui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.ic_like
import com.hulkdx.findprofessional.core.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CULikeButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(Res.drawable.ic_like),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                if (isSelected) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onError
            )
        )
    }
}

@Preview
@Composable
private fun CULikeButtonPreview() {
    AppTheme {
        CULikeButton(isSelected = false) {}
    }
}

@Preview
@Composable
private fun CULikeButtonSelectedPreview() {
    AppTheme {
        CULikeButton(isSelected = true) {}
    }
}
