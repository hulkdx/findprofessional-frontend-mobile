package com.hulkdx.findprofessional.common.utils

import android.os.Parcel
import android.os.Parcelable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.TypeParceler

actual typealias CommonParcelable = Parcelable
actual typealias CommonParceler<T> = Parceler<T>
actual typealias CommonTypeParceler<T, P> = TypeParceler<T, P>

actual object LocalDateParceler : Parceler<LocalDate?> {
    override fun create(parcel: Parcel) = LocalDate.parse(parcel.readString()!!)
    override fun LocalDate?.write(parcel: Parcel, flags: Int) {
        this?.toString()?.let { parcel.writeString(it) }
    }
}

actual object LocalTimeParceler : Parceler<LocalTime?> {
    override fun create(parcel: Parcel) = LocalTime.parse(parcel.readString()!!)
    override fun LocalTime?.write(parcel: Parcel, flags: Int) {
        this?.toString()?.let { parcel.writeString(it) }
    }
}

actual object InstantParceler : Parceler<Instant?> {
    override fun create(parcel: Parcel) = Instant.parse(parcel.readString()!!)
    override fun Instant?.write(parcel: Parcel, flags: Int) {
        this?.toString()?.let { parcel.writeString(it) }
    }
}
