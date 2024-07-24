@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.signuppro

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.utils.Rule

fun launchSignUpProScreen(
    rule: Rule,
    block: SignUpProDsl.() -> Unit,
): SignUpProDsl {
    return SignUpProDsl(rule).apply(block)
}

class SignUpProDsl(
    private val rule: Rule,
) {

    fun verify(block: SignUpProVerify.() -> Unit) = SignUpProVerify(rule).apply(block)
}

class SignUpProVerify(
    private val rule: Rule,
) {
}
