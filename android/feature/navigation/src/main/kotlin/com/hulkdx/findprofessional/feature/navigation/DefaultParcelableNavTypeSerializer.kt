package com.hulkdx.findprofessional.feature.navigation


import android.os.BadParcelableException
import android.os.Parcel
import android.os.Parcelable
import java.lang.reflect.Modifier
import java.nio.charset.StandardCharsets
import java.util.Base64

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

        return Base64.getUrlEncoder().encodeToString(bytes)
    }

    private fun <T> base64ToParcelable(base64: String, creator: Parcelable.Creator<T>): T {
        val bytes = Base64.getUrlDecoder().decode(base64.toByteArray(StandardCharsets.UTF_8))
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