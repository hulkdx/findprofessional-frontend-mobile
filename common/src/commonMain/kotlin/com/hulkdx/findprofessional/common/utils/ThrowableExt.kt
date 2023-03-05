package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.getPlatformSpecific
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.ktor.client.plugins.*

internal fun Throwable.generalError(): StringDesc {
    return if (getPlatformSpecific().isDebug()) {
        if (this is ClientRequestException) {
            message.desc()
        } else {
            stackTraceToString().desc()
        }
    } else {
        MR.strings.generalError.desc()
    }
}
