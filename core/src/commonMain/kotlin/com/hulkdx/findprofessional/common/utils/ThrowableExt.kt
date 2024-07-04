package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.generalError

fun Throwable.generalError(): StringOrRes {
    return Res.string.generalError.toStringOrRes()
//    return if (isDebug()) {
//        if (this is ClientRequestException) {
//            message.toStringOrRes()
//        } else {
//            stackTraceToString().toStringOrRes()
//        }
//    } else {
//        Res.string.generalError.toStringOrRes()
//    }
}
