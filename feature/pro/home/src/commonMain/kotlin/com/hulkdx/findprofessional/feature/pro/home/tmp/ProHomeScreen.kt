package com.hulkdx.findprofessional.feature.pro.home.tmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CuDropDownMenu
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.editAvailableTime
import com.hulkdx.findprofessional.core.theme.h2
import com.hulkdx.findprofessional.core.theme.h3Medium
import com.hulkdx.findprofessional.core.utils.TimeUtils
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditAvailableTimeScreen() {
    // TODO: move to viewmodel
    val options = remember { (0..<24 * 60 step 30).map { TimeUtils.formattedTime(it) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("TODO")
    ) {
        Text(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .align(CenterHorizontally),
            text = stringResource(Res.string.editAvailableTime),
            style = h2,
        )
        Row(Modifier.padding(start = 16.dp)) {
            CuDropDownMenu(
                modifier = Modifier
                    .weight(1F),
                options = options,
            )
            Text(
                modifier = Modifier.align(CenterVertically)
                    .padding(horizontal = 6.dp),
                text = "-",
                style = h3Medium
            )
            CuDropDownMenu(
                modifier = Modifier
                    .weight(1F),
                options = options,
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                )
            }
        }
    }
}
