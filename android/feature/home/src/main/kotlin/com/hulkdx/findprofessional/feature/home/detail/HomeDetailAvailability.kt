@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.utils.Availability
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body3
import com.hulkdx.findprofessional.feature.home.detail.HomeScreenDimens.outerHorizontalPadding
import com.hulkdx.findprofessional.resources.MR

internal fun LazyListScope.Availability(availabilities: Availability?) {
    if (availabilities == null) return
    item { AvailabilityHeader() }
}

@Composable
private fun AvailabilityHeader() {
    Text(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 8.dp,
                start = outerHorizontalPadding.dp,
            ),
        style = body1Medium,
        text = stringResource(MR.strings.availability.resourceId),
    )
}

@Preview
@Composable
fun AvailabilityPreview() {
    AppTheme {
        LazyColumn(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
            Availability(
                listOf(
                    listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                    listOf("00-04", "0", "0", "0", "0", "0", "0"),
                    listOf("04-08", "0", "1", "0", "0", "0", "0"),
                    listOf("08-12", "0", "0", "2", "0", "0", "0"),
                    listOf("12-16", "0", "0", "0", "3", "0", "0"),
                    listOf("16-20", "0", "0", "0", "0", "0", "0"),
                    listOf("20-24", "0", "0", "0", "0", "4", "0"),
                ),
            )
        }
    }
}
