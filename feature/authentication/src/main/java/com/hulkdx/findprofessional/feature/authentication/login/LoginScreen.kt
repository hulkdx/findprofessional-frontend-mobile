package com.hulkdx.findprofessional.feature.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.AppColor
import com.hulkdx.findprofessional.R
import com.hulkdx.findprofessional.ThemeColor
import com.hulkdx.findprofessional.feature.authentication.R as R1

@Composable
@Preview
fun LoginScreen() {
    Column(Modifier.fillMaxSize()) {
        LoginHeader()

        // TODO:
        Box {
            Text("smith@gmail.com")
        }
    }
}

@Composable
private fun LoginHeader() {
    Box(Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(R1.drawable.login_background),
            contentDescription = null
        )
        Column(
            Modifier
                .padding(
                    bottom = 40.dp
                )
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 76.dp,
                    start = 18.dp,
                    end = 146.dp,
                ),
                text = stringResource(id = R.string.letsGetStarted),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.inter_regular, FontWeight.Normal),
                    Font(R.font.inter_bold, FontWeight.Bold),
                    Font(R.font.inter_light, FontWeight.Light),
                    Font(R.font.inter_medium, FontWeight.Medium),
                ),
                color = AppColor.White,
            )
            Divider(
                modifier = Modifier.padding(
                    top = 24.dp,
                ),
                color = AppColor.White,
                thickness = 2.dp,
            )
            Text(
                modifier = Modifier.padding(
                    top = 30.dp,
                    start = 18.dp,
                    end = 80.dp,
                ),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.inter_regular, FontWeight.Normal),
                    Font(R.font.inter_bold, FontWeight.Bold),
                    Font(R.font.inter_light, FontWeight.Light),
                    Font(R.font.inter_medium, FontWeight.Medium),
                ),
                style = LocalTextStyle.current.copy(
                    lineHeight = 23.sp,
                ),
                text = stringResource(id = R.string.weAreHappy),
                color = AppColor.White,
            )
        }
    }
}
