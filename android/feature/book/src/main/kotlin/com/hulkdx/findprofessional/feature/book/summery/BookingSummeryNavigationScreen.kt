package com.hulkdx.findprofessional.feature.book.summery

import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.navigation.navtype.NavTypeParcelable
import com.hulkdx.findprofessional.feature.navigation.screen.BasicNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.screen.Content


class BookingSummeryNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { BookingSummeryScreen() }

    override val route: String
        get() = "${this.javaClass.name}/{$ARG1}/{$ARG2}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(ARG1) { type = ProNavType() },
            navArgument(ARG2) { type = TimeNavType() },
        )

    fun destination(professional: Professional, time: SelectedTimes): String {
        return this.javaClass.name + "/" + ProNavType().encodeValue(professional) +
                "/" + TimeNavType().encodeValue(time)
    }

    private class ProNavType : NavTypeParcelable<Professional>(Professional::class.java)
    private class TimeNavType : NavTypeParcelable<SelectedTimes>(SelectedTimes::class.java)

    companion object {
        const val ARG1 = "p"
        const val ARG2 = "t"

        fun professional(bundle: Bundle?): Professional {
            requireNotNull(bundle)
            return requireNotNull(
                BundleCompat.getParcelable(
                    bundle,
                    ARG1,
                    Professional::class.java
                )
            )
        }

        fun time(bundle: Bundle?): SelectedTimes {
            requireNotNull(bundle)
            return requireNotNull(
                BundleCompat.getParcelable(
                    bundle,
                    ARG2,
                    SelectedTimes::class.java
                )
            )
        }
    }
}
