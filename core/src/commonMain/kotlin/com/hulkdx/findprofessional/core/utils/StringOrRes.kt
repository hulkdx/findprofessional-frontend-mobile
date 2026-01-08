package com.hulkdx.findprofessional.core.utils

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.utils.serializer.StringResourceSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.jvm.JvmInline

@Serializable
data class StringOrRes(
    val string: String? = null,
    @Serializable(with = StringResourceSerializer::class)
    val res: StringResource? = null,
    val resArgs: List<FormatArg>? = null,
) {
    @Composable
    fun localized(): String {
        return string ?: stringResource(res!!)
    }

    sealed interface FormatArg {
        @Serializable
        @JvmInline
        value class Str(val v: String) : FormatArg

        @Serializable
        @JvmInline
        value class IntArg(val v: Int) : FormatArg

        @Serializable
        @JvmInline
        value class LongArg(val v: Long) : FormatArg

        @Serializable
        @JvmInline
        value class DoubleArg(val v: Double) : FormatArg

        @Serializable
        @JvmInline
        value class BoolArg(val v: Boolean) : FormatArg
    }

    companion object {
        fun createStringResource(
            res: StringResource,
            vararg args: FormatArg,
        ): StringOrRes {
            return StringOrRes(
                res = res,
                resArgs = args.toList(),
            )
        }
    }
}

fun String.toStringOrRes() = StringOrRes(string = this)
fun StringResource.toStringOrRes() = StringOrRes(res = this)
