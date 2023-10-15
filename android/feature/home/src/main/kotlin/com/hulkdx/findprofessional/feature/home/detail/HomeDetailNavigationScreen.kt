package com.hulkdx.findprofessional.feature.home.detail

import android.os.Bundle
import android.util.Log
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.NavTypeParcelable


class HomeDetailNavigationScreen : SlideNavigationScreen() {
    override val content: Content = {
        val professional = it.arguments.professional()
        HomeDetailScreen()
    }

    override val route: String
        get() = "${this.javaClass.name}/{p}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument("p") {
                type = HomeNavType()
            }
        )

    fun destination(professional: Professional): String {
        return this.javaClass.name + "/" + HomeNavType().encodeValue(professional)
    }

    private class HomeNavType : NavTypeParcelable<Professional>(Professional::class.java)
}

fun Bundle?.professional(): Professional {
    return requireNotNull(this?.getParcelable("p"))
}
