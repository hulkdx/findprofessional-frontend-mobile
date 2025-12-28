package com.hulkdx.findprofessional.feature.booking.confirmation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.close
import com.hulkdx.findprofessional.core.resources.paymentReceived
import com.hulkdx.findprofessional.core.resources.paymentsUnderReview
import com.hulkdx.findprofessional.core.ui.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body2
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookingConfirmationScreen(
    viewModel: BookingConfirmationViewModel = koinViewModel(),
) {
    BookingConfirmationScreen(
        onCloseClicked = viewModel::onCloseClicked,
    )
}

@Composable
fun BookingConfirmationScreen(onCloseClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .testTag("BookingConfirmationScreen"),
    ) {
        SuccessImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.paymentReceived),
            style = h1Medium,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(Res.string.paymentsUnderReview),
            style = body2,
            color = MaterialTheme.colorScheme.outline,
        )
        Spacer(Modifier.weight(1f))
        CUFilledButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.close),
            onClick = onCloseClicked,
        )
    }
}

@Composable
private fun SuccessImage(
    modifier: Modifier = Modifier,
) {
    val circleColor = MaterialTheme.colorScheme.primary
    val checkColor = MaterialTheme.colorScheme.surfaceVariant

    Canvas(modifier = modifier.size(96.dp)) {
        val strokeWidth = size.minDimension * 0.12f
        drawCircle(color = circleColor)

        val path = Path().apply {
            moveTo(size.width * 0.28f, size.height * 0.55f)
            lineTo(size.width * 0.44f, size.height * 0.7f)
            lineTo(size.width * 0.74f, size.height * 0.36f)
        }

        drawPath(
            path = path,
            color = checkColor,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
            ),
        )
    }
}

@Preview
@Composable
private fun BookingConfirmationScreenPreview() {
    AppTheme {
        BookingConfirmationScreen(
            onCloseClicked = {},
        )
    }
}
