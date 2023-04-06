package com.hulkdx.findprofessional.utils

import org.koin.java.KoinJavaComponent


inline fun <reified T> get(): T {
    return KoinJavaComponent.get(T::class.java)
}

inline fun <reified T> getAll(): List<T> {
    return KoinJavaComponent.getKoin().getAll()
}
