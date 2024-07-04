package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock

fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = Clock.System.now().toEpochMilliseconds()
        if (now - latest >= 3000) {
            onClick()
            latest = now
        }
    }
}
