@file:OptIn(ExperimentalUuidApi::class)

package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

// TODO: Kotlin 2.3.0 will have it Uuid.generateV7
fun generateUuidV7(): String {
    val timeStamp = Clock.System.now().toEpochMilliseconds()
    val base = Uuid.random()
    return base.toLongs { randomValue, leastSignificantBits ->
        val shiftedRightBits = timeStamp shl 16
        val left = randomValue and 0x0000_0000_0000_0FFFL
        val result = (left or 0x0000_0000_0000_7000L) or shiftedRightBits

        Uuid.fromLongs(
            mostSignificantBits = result,
            leastSignificantBits = leastSignificantBits,
        )
    }.toString()
}
