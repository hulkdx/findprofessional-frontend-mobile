package com.hulkdx.findprofessional.common.utils

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

// TODO: temporary function, will be removed once iOS is converted to compose
fun StringOrRes.localized(): String {
    // TODO: remove runBlocking
    return string ?: res!!.localized()
}

// TODO: temporary function, will be removed once iOS is converted to compose
fun StringResource.localized(): String {
    return runBlocking { getString(this@localized) }
}
