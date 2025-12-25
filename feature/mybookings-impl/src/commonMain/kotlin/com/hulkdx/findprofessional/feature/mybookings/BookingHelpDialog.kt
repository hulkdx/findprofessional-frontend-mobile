package com.hulkdx.findprofessional.feature.mybookings

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalUriHandler
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.contactSupport
import com.hulkdx.findprofessional.core.resources.reportProblem
import com.hulkdx.findprofessional.core.ui.theme.body1
import com.hulkdx.findprofessional.core.utils.CONTACT_SUPPORT_URL
import com.hulkdx.findprofessional.core.utils.REPORT_PROBLEM_URL
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookingHelpDialog(onDismiss: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onDismiss()
                    uriHandler.openUri(CONTACT_SUPPORT_URL)
                },
            ) {
                Text(
                    text = stringResource(Res.string.contactSupport),
                    style = body1,
                    color = Black,
                )
            }
        },
        dismissButton = {
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onDismiss()
                    uriHandler.openUri(REPORT_PROBLEM_URL)
                },
            ) {
                Text(
                    text = stringResource(Res.string.reportProblem),
                    style = body1,
                    color = Black,
                )
            }
        },
    )
}
