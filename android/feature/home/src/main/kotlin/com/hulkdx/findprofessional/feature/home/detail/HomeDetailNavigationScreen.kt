package com.hulkdx.findprofessional.feature.home.detail

import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.navtype.NavTypeParcelable

class HomeDetailNavigationScreen : SlideNavigationScreen() {
    override val content: Content = {
        HomeDetailScreen()
    }

    override val route: String
        get() = "${this.javaClass.name}/{$ARG1}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(ARG1) {
                type = HomeNavType()
            }
        )

    fun destination(professional: Professional): String {
        return this.javaClass.name + "/" + HomeNavType().encodeValue(professional)
    }

    private class HomeNavType : NavTypeParcelable<Professional>(Professional::class.java)

    companion object {
        const val ARG1 = "p"

        fun professional(bundle: Bundle?): Professional {
            requireNotNull(bundle)
            return requireNotNull(BundleCompat.getParcelable(bundle, ARG1, Professional::class.java))
        }
    }
}
