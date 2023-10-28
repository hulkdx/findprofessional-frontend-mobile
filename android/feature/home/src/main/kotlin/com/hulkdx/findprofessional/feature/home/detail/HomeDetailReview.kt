@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.resources.MR


internal fun LazyListScope.Review(professional: Professional) {
    item { ReviewHeader(professional) }
    items(professional.reviews) {
        ReviewContent()
    }
}

@Composable
private fun ReviewHeader(professional: Professional) {
    Header(
        modifier = Modifier,
        text = professional.totalReviews + " " + stringResource(MR.strings.reviews.resourceId)
    )
}

@Composable
private fun ReviewContent() {
}

@Preview
@Composable
private fun ReviewContentPreview() {
    AppTheme {
        ReviewContent()
    }
}
