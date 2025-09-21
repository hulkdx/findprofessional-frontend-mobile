package com.hulkdx.findprofessional.core.utils

import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.generalError

fun Throwable.generalError(): StringOrRes {
    return if (isDebug()) {
        stackTraceToString().toStringOrRes()
    } else {
        Res.string.generalError.toStringOrRes()
    }
}

inline fun getGeneralErrorOrNull(block: () -> Unit): StringOrRes? {
    try {
        block()
        return null
    } catch (e: Throwable) {
        val error = e.generalError()
        return error
    }
}

