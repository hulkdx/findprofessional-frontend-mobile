package com.hulkdx.findprofessional.feature.navigation.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.DividerColor1
import com.hulkdx.findprofessional.core.theme.body3

@Composable
internal fun AppNavigationBarInternal(
    modifier: Modifier = Modifier,
    items: List<NavData>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        items.forEachIndexed { index, item ->
            if (index != 0) {
                AppNavBarDivider()
            }

            AppNavBarItem(
                text  = item.text,
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
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val color = if (selected) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    Column(
        Modifier
            .weight(1F)
            .fillMaxHeight()
            .clickable(onClick=onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = icon,
            contentDescription = text,
            tint = color
        )
        Text(
            text = text,
            color = color,
            style = body3,
        )
    }
}

@Composable
private fun AppNavBarDivider() {
    Divider(
        color = DividerColor1,
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 16.dp)
            .width(1.dp)
    )
}

@Preview
@Composable
private fun AppNavigationBarPreview() {
    AppTheme {
        AppNavigationBar()
    }
}
