package com.hulkdx.findprofessional.core.utils

import android.util.Log


fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = System.currentTimeMillis()
        Log.d("Saba", "now = $now, latest=$latest")
        if (now - latest >= 3000) {
            Log.d("Saba", "onClick")
            onClick()
            latest = now
        }
    }
}
