package com.hulkdx.findprofessional.feature.navigation.navtype

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.navigation.navtype.util.base64ToParcelable
import com.hulkdx.findprofessional.feature.navigation.navtype.util.parcelableToBase64
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("ObsoleteSdkInt")
private val isRunningOnUnitTests = Build.VERSION.SDK_INT == 0


open class NavTypeParcelable<T : Parcelable>(
    private val jClass: Class<out T>,
) : NavType<T>(false) {

    override fun get(bundle: Bundle, key: String): T? {
        return BundleCompat.getParcelable(bundle, key, jClass)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): T {
        return value.base64ToParcelable(jClass) as T
    }

    fun encodeValue(value: Professional): String {
        return if (!isRunningOnUnitTests) {
            Uri.encode(value.parcelableToBase64())
        } else {
            URLEncoder.encode(value.parcelableToBase64(), StandardCharsets.UTF_8.toString())
        }
    }
}
