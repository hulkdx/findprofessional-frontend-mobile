package com.hulkdx.findprofessional.utils

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers
import org.hamcrest.CoreMatchers.anything
import org.junit.rules.ExternalResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class PlatformPopupRule : ExternalResource() {

    override fun before() {
        closePlatformPopup()
    }

    private fun closePlatformPopup() {
        onData(anything())
            .inRoot(RootMatchers.isPlatformPopup())
            .atPosition(1)
            .perform(click())
    }
}
