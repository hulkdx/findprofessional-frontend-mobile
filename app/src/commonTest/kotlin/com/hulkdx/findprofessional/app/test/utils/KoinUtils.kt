package com.hulkdx.findprofessional.app.test.utils

import org.koin.mp.KoinPlatformTools

inline fun <reified T : Any> get(): T {
    return KoinPlatformTools.defaultContext().get().get<T>()
}
