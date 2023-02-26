package com.hulkdx.findprofessional.utils

import com.hulkdx.findprofessional.navigation.NavigatorImpl
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.java.KoinJavaComponent

class UiTestRule : TestRule {

    private val navigator by KoinJavaComponent.inject<NavigatorImpl>(NavigatorImpl::class.java)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } finally {
                    tearDown()
                }
            }
        }
    }

    private fun tearDown() {
        navigator.screenState.value = null
    }
}
