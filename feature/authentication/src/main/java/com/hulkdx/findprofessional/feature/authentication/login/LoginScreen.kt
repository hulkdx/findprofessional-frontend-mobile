package com.hulkdx.findprofessional.feature.authentication.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h1
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.core.utils.bold
import com.hulkdx.findprofessional.feature.authentication.R
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import org.koin.androidx.compose.get

@Composable
fun LoginScreen(
    navigator: Navigator = get()
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onPrimary)
    ) {
        LoginHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            EmailTextField(
                value = username.value,
                onValueChanged = { username.value = it }
            )

            PasswordTextField(
                value = password.value,
                onValueChanged = { password.value = it }
            )

            SignInButton {
            }

            SignUpButton {
                navigator.navigate(SignUpNavigationScreen())
            }
        }
    }
}

@Composable
private fun LoginHeader() {
    Box(Modifier.fillMaxWidth()) {
        LoginHeaderBackgroundImage()
        Column {
            LoginHeaderTitle()
            LoginHeaderDivider()
            LoginHeaderDescription()
        }
    }
}

@Composable
private fun BoxScope.LoginHeaderBackgroundImage() {
    Image(
        modifier = Modifier.matchParentSize(),
        contentScale = ContentScale.FillBounds,
        painter = painterResource(R.drawable.login_background),
        contentDescription = null,
    )
}

@Composable
private fun LoginHeaderTitle() {
    Text(
        modifier = Modifier.padding(
            top = 60.dp,
            start = 18.dp,
            end = 38.dp,
        ),
        maxLines = 2,
        text = stringResource(id = R.string.letsGetStarted),
        color = MaterialTheme.colors.onPrimary,
        style = h1,
    )
}

@Composable
private fun LoginHeaderDivider() {
    Divider(
        modifier = Modifier.padding(
            start = 19.dp,
            end = 46.dp,
            top = 8.dp,
        ),
        color = MaterialTheme.colors.onPrimary
            .copy(alpha = 0.25f),
        thickness = 2.dp,
    )
}

@Composable
private fun LoginHeaderDescription() {
    Text(
        modifier = Modifier.padding(
            top = 24.dp,
            start = 18.dp,
            end = 80.dp,
            bottom = 24.dp,
        ),
        maxLines = 2,
        text = stringResource(id = R.string.weAreHappy),
        color = MaterialTheme.colors.onSecondary,
        style = body1,
    )
}

@Composable
private fun EmailTextField(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CommonTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
            )
            .height(50.dp),
        hint = stringResource(id = R.string.email),
        leadingIconDrawable = R.drawable.ic_password,
        leadingIconContentDescription = stringResource(id = R.string.email),
        value = value,
        onValueChanged = onValueChanged
    )
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CommonTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            )
            .height(50.dp),
        hint = stringResource(id = R.string.password),
        leadingIconDrawable = R.drawable.ic_email,
        leadingIconContentDescription = stringResource(id = R.string.password),
        value = value,
        onValueChanged = onValueChanged
    )
}

@Composable
private fun CommonTextField(
    modifier: Modifier,
    hint: String,
    @DrawableRes leadingIconDrawable: Int,
    leadingIconContentDescription: String,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        leadingIcon = {
            Image(
                painter = painterResource(leadingIconDrawable),
                contentDescription = leadingIconContentDescription
            )
        },
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = hint,
                style = body2,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = LightGrey,
        ),
    )
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 36.dp,
            ),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = stringResource(id = R.string.signIn),
            style = h3,
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 62.dp,
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        TextButton(
            onClick = onClick,
            Modifier.testTag(stringResource(id = R.string.signUp))
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.dontHaveAnAccount))
                    append(" ")
                    bold {
                        append(stringResource(id = R.string.signUp))
                    }
                },
                style = body1,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}
