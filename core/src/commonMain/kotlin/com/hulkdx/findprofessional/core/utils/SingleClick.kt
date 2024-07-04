package com.hulkdx.findprofessional.core.utils

fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = System.currentTimeMillis()
        if (now - latest >= 3000) {
            onClick()
            latest = now
        }
    }
}
