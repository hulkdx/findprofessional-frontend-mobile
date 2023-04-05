package com.hulkdx.findprofessional.other

import com.hulkdx.findprofessional.ui.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.Rule

fun launchMainScreen(
    rule: Rule,
    email: String,
    password: String,
) {
    launchLoginScreen(rule) {
        typeEmail(email)
        typePassword(password)
        pressSignInButton()
    }
}

