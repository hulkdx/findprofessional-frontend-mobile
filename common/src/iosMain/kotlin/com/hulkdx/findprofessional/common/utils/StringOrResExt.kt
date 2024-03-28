package com.hulkdx.findprofessional.common.utils

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

// TODO: temporary function, will be removed once iOS is converted to compose
fun StringOrRes.localized(): String {
    // TODO: remove runBlocking
    return string ?: runBlocking { getString(res!!) }
}
