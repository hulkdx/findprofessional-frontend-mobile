package com.hulkdx.findprofessional.feature.pro.schedule.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingPast
import com.hulkdx.findprofessional.core.resources.bookingUpcoming
import com.hulkdx.findprofessional.core.ui.theme.body2Medium
import com.hulkdx.findprofessional.feature.pro.schedule.model.Segment
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun Header(
    segment: Segment,
    onSelected: (Segment) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(18.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        SegmentedItem(
            text = Res.string.bookingUpcoming,
            isSelected = segment == Segment.Upcoming,
            onClick = { onSelected(Segment.Upcoming) },
            modifier = Modifier.weight(1f),
        )
        SegmentedItem(
            text = Res.string.bookingPast,
            isSelected = segment == Segment.Past,
            onClick = { onSelected(Segment.Past) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SegmentedItem(
    text: StringResource,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(32.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.surfaceVariant
                } else {
                    MaterialTheme.colorScheme.onPrimary
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(text),
            style = body2Medium,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        )
    }
}
