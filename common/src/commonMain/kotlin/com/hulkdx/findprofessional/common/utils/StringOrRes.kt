package com.hulkdx.findprofessional.common.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


//@CommonParcelize
// @CommonTypeParceler<StringResource, LocalTimeParceler>
data class StringOrRes(
    val string: String? = null,
    val res: StringResource? = null,
)
//    : CommonParcelable
{
    @Composable
    inline fun localized(): String {
        return string ?: stringResource(res!!)
    }
}

fun String.toStringOrRes() = StringOrRes(string = this)
fun StringResource.toStringOrRes() = StringOrRes(res = this)
