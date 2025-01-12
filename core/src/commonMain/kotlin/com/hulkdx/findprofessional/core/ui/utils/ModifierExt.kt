package com.hulkdx.findprofessional.core.ui.utils

import androidx.compose.ui.Modifier

inline fun Modifier.`if`(condition: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(block(Modifier))
    } else {
        this
    }
}
