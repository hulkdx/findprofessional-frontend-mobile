package com.hulkdx.findprofessional.feature.home.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.DefaultParcelableNavTypeSerializer
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


class HomeDetailNavigationScreen : SlideNavigationScreen() {
    override val content: Content = {
        val professional = it.arguments.professional()
        HomeDetailScreen()
    }

    override val route: String
        get() = this.javaClass.name + "/{p}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument("p") {
                type = HomeNavType()
            }
        )

    fun destination(professional: Professional): String {
        return this.javaClass.name + "/" + HomeNavType().serializeValue(professional)
    }
}

fun Bundle?.professional(): Professional {
    return requireNotNull(this?.getParcelable("p"))
}

class HomeNavType : NavType<Professional>(false) {
    override fun get(bundle: Bundle, key: String): Professional? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Professional {
        return DefaultParcelableNavTypeSerializer(Professional::class.java).fromRouteString(value) as Professional
    }

    override fun put(bundle: Bundle, key: String, value: Professional) {
        bundle.putParcelable(key, value)
    }

    fun serializeValue(value: Professional): String {
        return encodeForRoute(
            DefaultParcelableNavTypeSerializer(Professional::class.java).toRouteString(
                value
            )
        )
    }
}

@SuppressLint("ObsoleteSdkInt")
private val isRunningOnUnitTests = Build.VERSION.SDK_INT == 0


fun encodeForRoute(arg: String): String {
    return if (!isRunningOnUnitTests) {
        Uri.encode(arg)
    } else {
        URLEncoder.encode(arg, StandardCharsets.UTF_8.toString())
    }
}

