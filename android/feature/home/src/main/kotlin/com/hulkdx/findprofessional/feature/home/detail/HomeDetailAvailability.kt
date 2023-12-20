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
    item { AvailabilityContentTop() }
    items(availabilities) {
        AvailabilityContentRow(it)
    }
    item { AvailabilityContentBottom() }
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

@Composable
private fun AvailabilityContentTop() {
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(16.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
private fun AvailabilityContentRow(rows: List<String>) {
    Row(
        Modifier
            .padding(start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(start = 12.dp, end = 25.dp)
    ) {
        rows.forEachIndexed { index, item ->
            key(index) {
                AvailabilityContentElement(item)
            }
        }
    }
}

@Composable
private fun RowScope.AvailabilityContentElement(row: String) {
    when (row) {
        "0" -> {
            AvailabilityBox(Color(0xFFF2F2F2))
        }

        "1" -> {
            AvailabilityBox(Color(0xFFE1F0D1))
        }

        "2" -> {
            AvailabilityBox(Color(0xFFC1DDA1))
        }

        "3" -> {
            AvailabilityBox(Color(0xFFA8D279))
        }

        "4" -> {
            AvailabilityBox(Color(0xFF8FC750))
        }

        else -> {
            AvailabilityText(row)
        }
    }
}

@Composable
private fun RowScope.AvailabilityBox(color: Color) {
    Box(
        Modifier
            .weight(1F)
            .fillMaxWidth()
            .height(30.dp)
            .padding(6.dp)
            .border(1.dp, Color(0xFF797979))
            .background(color)
    )
}

@Composable
private fun RowScope.AvailabilityText(row: String) {
    Text(
        text = row,
        modifier = Modifier
            .weight(1F)
            .align(Alignment.CenterVertically),
        style = body3,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun AvailabilityContentBottom() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp),
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
