package com.hulkdx.findprofessional.feature.book.summery.stripe

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import kotlin.time.Duration


@Composable
fun AutoDismissPaymentSheet(
    timeout: Duration,
    context: Context,
    isTimedOut: MutableState<Boolean>,
) {
    val autoDismissManager = remember(context) {
        PaymentSheetAutoDismissManager(
            application = context.applicationContext as Application,
            timeout = timeout,
            isTimedOut,
        )
    }

    DisposableEffect(autoDismissManager) {
        autoDismissManager.register()
        onDispose {
            autoDismissManager.unregister()
        }
    }
}

/**
 * From: https://stackoverflow.com/questions/76374279/how-to-close-stripe-paymentsheet-programatically-from-code-in-android
 * and: https://github.com/stripe/stripe-react-native/pull/1287/files
 */
private class PaymentSheetAutoDismissManager(
    private val application: Application,
    private val timeout: Duration,
    private val isTimedOut: MutableState<Boolean>,
) : Application.ActivityLifecycleCallbacks {

    private val handler = Handler(Looper.getMainLooper())
    private var timeoutRunnable: Runnable? = null
    private var isRegistered = false

    fun register() {
        if (isRegistered) return
        application.registerActivityLifecycleCallbacks(this)
        isRegistered = true
    }

    fun unregister() {
        if (!isRegistered) return
        cancelTimeout()
        application.unregisterActivityLifecycleCallbacks(this)
        isRegistered = false
    }

    override fun onActivityResumed(activity: Activity) {
        if (!activity.isStripePaymentSheet()) return
        scheduleTimeout(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        if (!activity.isStripePaymentSheet()) return
        cancelTimeout()
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (!activity.isStripePaymentSheet()) return
        cancelTimeout()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
    override fun onActivityStarted(activity: Activity) = Unit
    override fun onActivityStopped(activity: Activity) = Unit
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    private fun scheduleTimeout(activity: Activity) {
        cancelTimeout()

        val runnable = Runnable {
            if (!activity.isFinishing && !activity.isDestroyed) {
                activity.finish()
                isTimedOut.value = true
            }
        }
        timeoutRunnable = runnable
        handler.postDelayed(runnable, timeout.inWholeMilliseconds)
    }

    private fun cancelTimeout() {
        timeoutRunnable?.let(handler::removeCallbacks)
        timeoutRunnable = null
    }

    private fun Activity.isStripePaymentSheet(): Boolean {
        return javaClass.name.contains("stripe")
    }
}
