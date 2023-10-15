package com.hulkdx.findprofessional.feature.navigation.navtype.util

import android.os.Parcel
import android.os.Parcelable
import java.nio.charset.StandardCharsets
import java.util.Base64

internal fun ByteArray.toParcel(): Parcel {
    val parcel = Parcel.obtain()
    parcel.unmarshall(this, 0, this.size)
    parcel.setDataPosition(0)
    return parcel
}

internal fun Parcelable.toByteArray(): ByteArray {
    val parcel = Parcel.obtain()
    writeToParcel(parcel, 0)
    val bytes = parcel.marshall()
    parcel.recycle()
    return bytes
}


fun Parcelable.parcelableToBase64(): String {
    return Base64.getUrlEncoder().encodeToString(toByteArray())
}

fun String.base64ToParcelable(jClass: Class<out Parcelable>): Parcelable {
    val creator = jClass.parcelableCreator
    val bytes = Base64.getUrlDecoder().decode(toByteArray(StandardCharsets.UTF_8))
    val parcel = bytes.toParcel()
    val base64ToParcelable = creator.createFromParcel(parcel)
    parcel.recycle()
    return base64ToParcelable
}

@Suppress("UNCHECKED_CAST")
private val <T> Class<T>.parcelableCreator
    get() : Parcelable.Creator<T> {
        val creatorField = getField("CREATOR")
        return creatorField.get(null) as Parcelable.Creator<T>
    }
