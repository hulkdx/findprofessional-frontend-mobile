package com.hulkdx.findprofessional.feature.book

import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.navigation.navtype.NavTypeParcelable
import com.hulkdx.findprofessional.feature.navigation.screen.BasicNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.screen.Content


class BookNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { BookScreen() }

    override val route: String
        get() = "${this.javaClass.name}/{$ARG1}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(ARG1) {
                type = ThisNavType()
            }
        )

    fun destination(professional: Professional): String {
        return this.javaClass.name + "/" + ThisNavType().encodeValue(professional)
    }

    private class ThisNavType : NavTypeParcelable<Professional>(Professional::class.java)

    companion object {
        const val ARG1 = "p"

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
    }
}
