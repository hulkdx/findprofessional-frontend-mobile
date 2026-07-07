package com.hulkdx.findprofessional.feature.mybookings.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.cancellationNotSupported
import com.hulkdx.findprofessional.core.resources.cancellationNotSupportedTitle
import com.hulkdx.findprofessional.core.resources.close
import com.hulkdx.findprofessional.core.resources.reportProblem
import org.jetbrains.compose.resources.stringResource

@Composable
fun CancellationDialog(
    bookingId: String,
    onDismiss: () -> Unit,
    onClickReportProblem: () -> Unit,
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(Res.string.cancellationNotSupportedTitle))
        },
        text = {
            Text(stringResource(Res.string.cancellationNotSupported))
        },
        confirmButton = {
            TextButton(
                onClick = onClickReportProblem,
            ) {
                Text(stringResource(Res.string.reportProblem))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(Res.string.close))
            }
        }
    )
}
