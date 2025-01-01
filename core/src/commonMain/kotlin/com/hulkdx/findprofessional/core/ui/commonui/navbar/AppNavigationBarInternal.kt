package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body3
import com.hulkdx.findprofessional.core.utils.borderTop
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun AppNavigationBarInternal(
    modifier: Modifier = Modifier,
    items: List<NavData>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .borderTop(0.5.dp, MaterialTheme.colorScheme.onTertiary)
            .navigationBarsPadding()
            .height(AppNavigationBarDimens.Height.dp),
    ) {
        items.forEach { item ->
            AppNavBarItem(
                text = item.text,
                icon = item.icon,
                selected = item.selected,
                onClick = item.onClick,
            )
        }
    }
}

@Composable
private fun RowScope.AppNavBarItem(
    text: String,
    icon: Painter,
    selected: Boolean,
    onClick: (() -> Unit)?,
) {
    val color = if (selected) {
        Color.Black
    } else {
        Color(0xFFCAC9DA)
    }
    Column(
        Modifier
            .weight(1F)
            .fillMaxHeight()
            .run {
                if (onClick != null) {
                    clickable(onClick = onClick)
                } else this
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = icon,
            contentDescription = text,
            tint = color
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = text,
            color = color,
            style = body3,
        )
    }
}
