package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock

private const val DEBOUNCE_TIME_MS = 500

fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = Clock.System.now().toEpochMilliseconds()
        if (now - latest >= DEBOUNCE_TIME_MS) {
            onClick()
            latest = now
        }
    }
}

fun <T> singleClick(onClick: (T) -> Unit): (T) -> Unit {
    var latest: Long = 0
    return { d: T ->
        val now = Clock.System.now().toEpochMilliseconds()
        if (now - latest >= DEBOUNCE_TIME_MS) {
            onClick(d)
            latest = now
        }
    }
}
