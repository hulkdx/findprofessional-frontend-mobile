package com.hulkdx.findprofessional.feature.authentication.login

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.theme.LightGrey
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.core.utils.bold
import com.hulkdx.findprofessional.feature.authentication.R
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    navigator: Navigator = get(),
    viewModel: LoginViewModel = getViewModel(),
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onPrimary),
        verticalArrangement = Arrangement.Center
    ) {
        EmailTextField(
            value = username.value,
            onValueChanged = { username.value = it }
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = password.value,
            onValueChanged = { password.value = it }
        )

        SignInButton(
            modifier = Modifier.padding(top = 16.dp),
        ) {
        }

        SignUpButton(
            modifier = Modifier.padding(top = 32.dp),
        ) {
            navigator.navigate(SignUpNavigationScreen())
        }
    }
}

@Composable
private fun LoginHeaderDivider(modifier: Modifier) {
    Divider(
        modifier = modifier.padding(
            start = 19.dp,
            end = 46.dp,
        ),
        color = MaterialTheme.colors.onPrimary
            .copy(alpha = 0.25f),
        thickness = 2.dp,
    )
}

@Composable
private fun LoginHeaderDescription(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(
            start = 18.dp,
            end = 80.dp,
        ),
        maxLines = 2,
        text = stringResource(id = R.string.weAreHappy),
        color = MaterialTheme.colors.onSecondary,
        style = body1,
    )
}

@Composable
private fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CommonTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
            ),
        hint = stringResource(id = R.string.email),
        leadingIconDrawable = R.drawable.ic_password,
        leadingIconContentDescription = stringResource(id = R.string.email),
        value = value,
        onValueChanged = onValueChanged
    )
}

@Composable
private fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CommonTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
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
            backgroundColor = LightGrey,
        ),
    )
}

@Composable
private fun SignInButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
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
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
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
