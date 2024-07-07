package com.hulkdx.findprofessional.core.utils

import com.hulkdx.findprofessional.core.config.isDebug
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.generalError

fun Throwable.generalError(): StringOrRes {
    return if (isDebug()) {
        stackTraceToString().toStringOrRes()
    } else {
        Res.string.generalError.toStringOrRes()
    }
}
