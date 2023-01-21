package com.hulkdx.findprofessional.ui.screen.signup

import com.hulkdx.findprofessional.utils.Rule

fun launchSignUpScreen(
    rule: Rule,
    block: SignUpDsl.() -> Unit,
) = SignUpDsl(rule).apply(block)

class SignUpDsl(
    private val rule: Rule
) {
    fun verify(block: SignUpVerify.() -> Unit) = SignUpVerify(rule).apply(block)
}

class SignUpVerify(
    private val rule: Rule
) {
}
