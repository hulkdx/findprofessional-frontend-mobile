package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.isDebug
import com.hulkdx.findprofessional.common.resources.Res
import com.hulkdx.findprofessional.common.resources.generalError
import io.ktor.client.plugins.ClientRequestException

fun Throwable.generalError(): StringOrRes {
    return if (isDebug()) {
        if (this is ClientRequestException) {
            message.toStringOrRes()
        } else {
            stackTraceToString().toStringOrRes()
        }
    } else {
        Res.string.generalError.toStringOrRes()
    }
}
