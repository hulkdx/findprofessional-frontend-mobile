package com.hulkdx.findprofessional.feature.home.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.BadParcelableException
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen
import java.lang.reflect.Modifier
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


class HomeDetailNavigationScreen : SlideNavigationScreen() {
    override val content: Content = {
        val professional = requireNotNull(it.arguments?.getParcelable<Professional?>("p"))
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
        return encodeForRoute(DefaultParcelableNavTypeSerializer(Professional::class.java).toRouteString(value))
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

class DefaultParcelableNavTypeSerializer(
    private val jClass: Class<out Parcelable>,
) {

    fun toRouteString(value: Parcelable): String {
        return value.javaClass.name + "@" + value.toBase64()
    }

    fun fromRouteString(routeStr: String): Parcelable {
        val (className, base64) = routeStr.split("@").let { it[0] to it[1] }

        val creator = if (jClass.isFinal) {
            // Since we have this, small optimization to avoid additional reflection call of Class.forName
            jClass.parcelableCreator
        } else {
            // If our class is not final, then we must use the actual class from "className"
            parcelableClassForName(className).parcelableCreator
        }

        return base64ToParcelable(base64, creator)
    }

    private fun Parcelable.toBase64(): String {
        val parcel = Parcel.obtain()
        writeToParcel(parcel, 0)
        val bytes = parcel.marshall()
        parcel.recycle()

        return bytes.toBase64Str()
    }

    private fun <T> base64ToParcelable(base64: String, creator: Parcelable.Creator<T>): T {
        val bytes = base64.base64ToByteArray()
        val parcel = unmarshall(bytes)
        val result = creator.createFromParcel(parcel)
        parcel.recycle()
        return result
    }

    private fun unmarshall(bytes: ByteArray): Parcel {
        val parcel = Parcel.obtain()
        parcel.unmarshall(bytes, 0, bytes.size)
        parcel.setDataPosition(0)
        return parcel
    }

    @Suppress("UNCHECKED_CAST")
    private val <T> Class<T>.parcelableCreator
        get() : Parcelable.Creator<T> {
            return try {
                val creatorField = getField("CREATOR")
                creatorField.get(null) as Parcelable.Creator<T>
            } catch (e: Exception) {
                throw BadParcelableException(e)
            } catch (t: Throwable) {
                throw BadParcelableException(t.message)
            }
        }

    @Suppress("UNCHECKED_CAST")
    private fun parcelableClassForName(className: String): Class<out Parcelable> {
        return Class.forName(className) as Class<out Parcelable>
    }

    private val Class<out Parcelable>.isFinal
        get() =
            !isInterface && Modifier.isFinal(modifiers)
}

fun ByteArray.toBase64Str(): String {
    return java.util.Base64.getUrlEncoder().encodeToString(this)
}

fun String.base64ToByteArray(): ByteArray {
    return java.util.Base64.getUrlDecoder().decode(toByteArray(StandardCharsets.UTF_8))
}
