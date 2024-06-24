package com.hulkdx.findprofessional.common.utils

import org.jetbrains.compose.resources.StringResource

data class StringOrRes(
    val string: String? = null,
    val res: StringResource? = null,
)

fun String.toStringOrRes() = StringOrRes(string = this)
fun StringResource.toStringOrRes() = StringOrRes(res = this)
