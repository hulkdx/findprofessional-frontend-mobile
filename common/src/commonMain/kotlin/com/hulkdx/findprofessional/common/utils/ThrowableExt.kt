package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.isDebug
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.ktor.client.plugins.ClientRequestException

fun Throwable.generalError(): StringDesc {
    return if (isDebug()) {
        if (this is ClientRequestException) {
            message.desc()
        } else {
            stackTraceToString().desc()
        }
    } else {
        MR.strings.generalError.desc()
    }
}
