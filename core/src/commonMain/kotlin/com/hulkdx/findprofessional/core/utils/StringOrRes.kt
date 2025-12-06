package com.hulkdx.findprofessional.core.utils

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.utils.serializer.StringResourceSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Serializable
data class StringOrRes(
    val string: String? = null,
    @Serializable(with = StringResourceSerializer::class)
    val res: StringResource? = null,
) {
    @Composable
    fun localized(): String {
        return string ?: stringResource(res!!)
    }
}

fun String.toStringOrRes() = StringOrRes(string = this)
fun StringResource.toStringOrRes() = StringOrRes(res = this)
